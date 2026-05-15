package com.railway.ticket.management.system.service.implementation;

import com.railway.ticket.management.system.domain.ReturnPolicy;
import com.railway.ticket.management.system.domain.Schedule;
import com.railway.ticket.management.system.domain.Ticket;
import com.railway.ticket.management.system.domain.enums.TicketStatus;
import com.railway.ticket.management.system.repository.implementation.ReturnPolicyRepository;
import com.railway.ticket.management.system.repository.implementation.ScheduleRepository;
import com.railway.ticket.management.system.repository.implementation.TicketRepository;
import com.railway.ticket.management.system.service.ITicketService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
public class TicketService implements ITicketService {

    private final TicketRepository ticketRepository;
    private final ScheduleRepository scheduleRepository;
    private final ReturnPolicyRepository returnPolicyRepository;

    public TicketService(TicketRepository ticketRepository,
                         ScheduleRepository scheduleRepository,
                         ReturnPolicyRepository returnPolicyRepository) {
        this.ticketRepository = ticketRepository;
        this.scheduleRepository = scheduleRepository;
        this.returnPolicyRepository = returnPolicyRepository;
    }

    @Override
    public Ticket save(Ticket ticket) {
        ticketRepository.save(ticket);
        return ticket;
    }

    @Override
    public Optional<Ticket> findById(int id) {
        return ticketRepository.findById(id);
    }

    @Override
    public List<Ticket> findByUserId(int userId) {
        return ticketRepository.findByUserId(userId);
    }

    @Override
    public List<Ticket> findByScheduleId(int scheduleId) {
        return ticketRepository.findByScheduleId(scheduleId);
    }

    @Override
    public List<Ticket> findAll(int page, int size) {
        return ticketRepository.findAll(page, size);
    }

    @Override
    public int count() {
        return ticketRepository.count();
    }

    @Override
    public int update(Ticket ticket) {
        return ticketRepository.update(ticket);
    }

    @Override
    public int deleteById(int id) {
        return ticketRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Ticket bookTicket(Ticket ticket) {
        ticket.setBookingTime(LocalDateTime.now());
        ticket.setStatus(TicketStatus.BOOKED);
        ticket.setRefundAmount(0);
        ticketRepository.save(ticket);
        return ticket;
    }

    @Override
    @Transactional
    public Ticket returnTicket(int ticketId) {
        Optional<Ticket> optionalTicket = ticketRepository.findById(ticketId);
        if (optionalTicket.isEmpty()) {
            throw new RuntimeException("Ticket not found with id: " + ticketId);
        }
        Ticket ticket = optionalTicket.get();

        if (ticket.getStatus() == TicketStatus.RETURNED) {
            throw new RuntimeException("Ticket already returned with id: " + ticketId);
        }

        Optional<Schedule> schedule = scheduleRepository.findById(ticket.getScheduleId());
        if (schedule.isEmpty()) {
            throw new RuntimeException("Schedule not found for ticket id: " + ticketId);
        }

        LocalDateTime departureTime = schedule.get().getDepartureTime();
        LocalDateTime now = LocalDateTime.now();

        if (now.isAfter(departureTime)) {
            throw new RuntimeException("Cannot return ticket after departure for ticket id: " + ticketId);
        }

        long hoursDifference = Duration.between(now, departureTime).toHours();
        float refundAmount = getRefundAmount(ticket, hoursDifference);

        ticketRepository.updateTicketStatusAndRefund(
                ticketId,
                TicketStatus.RETURNED.name(),
                refundAmount,
                now
        );
        ticket.setStatus(TicketStatus.RETURNED);
        ticket.setRefundAmount(refundAmount);
        ticket.setActualReturnTimestamp(now);
        return ticket;
    }

    private float getRefundAmount(Ticket ticket, long hoursDifference) {
        List<ReturnPolicy> policies = returnPolicyRepository.getAll();

        float deductionPercentage = policies.stream()
                .filter(p -> hoursDifference >= p.getHoursBeforeDeparture())
                .findFirst()
                .map(ReturnPolicy::getDeductionPercentage)
                .orElse(100f);

        return ticket.getTotalAmount() * (1 - (deductionPercentage / 100));
    }

    @Override
    public boolean isSeatAvailable(int scheduleId, int coachId, String seatNumber) {
        return ticketRepository.isSeatAvailable(scheduleId, coachId, seatNumber);
    }

    @Override
    @Transactional
    public int updateTicketStatusAndRefund(int ticketId, String status, float refundAmount, LocalDateTime returnTime) {
        return ticketRepository.updateTicketStatusAndRefund(ticketId, status, refundAmount, returnTime);
    }
}

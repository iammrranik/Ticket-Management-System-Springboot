package com.railway.ticket.management.system.service.implementation;

import com.railway.ticket.management.system.domain.Ticket;
import com.railway.ticket.management.system.domain.enums.TicketStatus;
import com.railway.ticket.management.system.repository.implementation.TicketRepository;
import com.railway.ticket.management.system.service.ITicketService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TicketService implements ITicketService {

    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
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
    @Async
    @Transactional
    public synchronized Ticket bookTicket(Ticket ticket) {
        ticket.setBookingTime(LocalDateTime.now());
        ticket.setStatus(TicketStatus.CONFIRMED);
        ticket.setRefundAmount(0);
        ticketRepository.save(ticket);
        return ticket;
    }

    @Override
    @Async
    @Transactional
    public synchronized Ticket returnTicket(int ticketId) {
        Optional<Ticket> optionalTicket = ticketRepository.findById(ticketId);
        if (optionalTicket.isPresent()) {
            Ticket ticket = optionalTicket.get();
            ticket.setStatus(TicketStatus.RETURNED);
            ticket.setActualReturnTimestamp(LocalDateTime.now());
            float refundAmount = ticket.getTotalAmount() * 0.9f;
            ticket.setRefundAmount(refundAmount);
            ticketRepository.updateTicketStatusAndRefund(ticketId, TicketStatus.RETURNED.name(), refundAmount, LocalDateTime.now());
            return ticket;
        }
        return null;
    }

    @Override
    public boolean isSeatAvailable(int scheduleId, int coachId, String seatNumber) {
        return ticketRepository.isSeatAvailable(scheduleId, coachId, seatNumber);
    }

    @Override
    @Transactional
    public synchronized int updateTicketStatusAndRefund(int ticketId, String status, float refundAmount, LocalDateTime returnTime) {
        return ticketRepository.updateTicketStatusAndRefund(ticketId, status, refundAmount, returnTime);
    }
}

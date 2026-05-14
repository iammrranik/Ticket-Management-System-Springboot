package com.railway.ticket.management.system.repository;

import com.railway.ticket.management.system.domain.Ticket;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ITicketRepository {
    int save(Ticket ticket);
    Optional<Ticket> findById(int id);
    List<Ticket> findByUserId(int userId);
    List<Ticket> findByScheduleId(int scheduleId);
    List<Ticket> findAll(int page, int size);
    int count();
    int update(Ticket ticket);
    int deleteById(int id);
    int updateTicketStatusAndRefund(int ticketId, String status, float refundAmount, LocalDateTime returnTime);
    boolean isSeatAvailable(int scheduleId, int coachId, String seatNumber);
}

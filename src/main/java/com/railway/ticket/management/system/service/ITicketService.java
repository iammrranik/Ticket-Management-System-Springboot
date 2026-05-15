package com.railway.ticket.management.system.service;

import com.railway.ticket.management.system.domain.Ticket;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


public interface ITicketService {
    Ticket save(Ticket ticket);
    Optional<Ticket> findById(int id);
    List<Ticket> findByUserId(int userId);
    List<Ticket> findByScheduleId(int scheduleId);
    List<Ticket> findAll(int page, int size);
    int count();
    int update(Ticket ticket);
    int deleteById(int id);
    Ticket bookTicket(Ticket ticket);
    Ticket returnTicket(int ticketId);
    boolean isSeatAvailable(int scheduleId, int coachId, String seatNumber);
    int updateTicketStatusAndRefund(int ticketId, String status, float refundAmount, LocalDateTime returnTime);
}

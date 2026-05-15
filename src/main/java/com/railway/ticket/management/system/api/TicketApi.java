package com.railway.ticket.management.system.api;

import com.railway.ticket.management.system.domain.Ticket;
import com.railway.ticket.management.system.service.implementation.TicketService;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/ticket")
public class TicketApi {

    private final TicketService ticketService;

    public TicketApi(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping
    public void save(@Valid @RequestBody Ticket ticket) {this.ticketService.save(ticket);}

    @PutMapping
    public void update(@Valid @RequestBody Ticket ticket) {this.ticketService.update(ticket);}

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        int result = this.ticketService.deleteById(id);
        if (result > 0) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ticket> findById(@PathVariable int id) {
        return this.ticketService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Ticket> findAll(@RequestParam int page, @RequestParam int size) {
        return this.ticketService.findAll(page, size);
    }

    @GetMapping("/user/{userId}")
    public List<Ticket> findByUserId(@PathVariable int userId) {
        return this.ticketService.findByUserId(userId);
    }

    @GetMapping("/schedule/{scheduleId}")
    public List<Ticket> findByScheduleId(@PathVariable int scheduleId) {
        return this.ticketService.findByScheduleId(scheduleId);
    }

    @PostMapping("/book")
    public Ticket book(@Valid @RequestBody Ticket ticket) {return this.ticketService.bookTicket(ticket);}

    @PostMapping("/return/{id}")
    public Ticket returnTicket(@PathVariable int id) {return this.ticketService.returnTicket(id);}

    @GetMapping("/check-seat/{scheduleId}/{coachId}/{seatNumber}")
    public boolean checkSeat(@PathVariable int scheduleId,
                              @PathVariable int coachId,
                              @PathVariable String seatNumber) {
        return this.ticketService.isSeatAvailable(scheduleId, coachId, seatNumber);
    }
}

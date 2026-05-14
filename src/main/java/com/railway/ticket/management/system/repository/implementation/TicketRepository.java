package com.railway.ticket.management.system.repository.implementation;

import com.railway.ticket.management.system.domain.Ticket;
import com.railway.ticket.management.system.repository.ITicketRepository;
import com.railway.ticket.management.system.repository.mapper.TicketMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class TicketRepository implements ITicketRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private static final TicketMapper ticketMapper = new TicketMapper();

    public TicketRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public int save(Ticket ticket) {
        String sql = """
                INSERT INTO tickets (user_id, schedule_id, coach_id, seat_number, booking_time, total_amount, status, actual_return_timestamp, refund_amount)
                VALUES (:user_id, :schedule_id, :coach_id, :seat_number, :booking_time, :total_amount, :status, :actual_return_timestamp, :refund_amount)
                """;
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("user_id", ticket.getUserId())
                .addValue("schedule_id", ticket.getScheduleId())
                .addValue("coach_id", ticket.getCoachId())
                .addValue("seat_number", ticket.getSeatNumber())
                .addValue("booking_time", ticket.getBookingTime())
                .addValue("total_amount", ticket.getTotalAmount())
                .addValue("status", ticket.getStatus().name())
                .addValue("actual_return_timestamp", ticket.getActualReturnTimestamp())
                .addValue("refund_amount", ticket.getRefundAmount());
        return namedParameterJdbcTemplate.update(sql, params);
    }

    @Override
    public Optional<Ticket> findById(int id) {
        String sql = """
                SELECT *
                FROM tickets
                WHERE id = :id
                """;
        MapSqlParameterSource params = new MapSqlParameterSource("id", id);
        try {
            Ticket ticket = namedParameterJdbcTemplate.queryForObject(sql, params, ticketMapper);
            return Optional.ofNullable(ticket);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Ticket> findByUserId(int userId) {
        String sql = """
                SELECT *
                FROM tickets
                WHERE user_id = :user_id
                """;
        MapSqlParameterSource params = new MapSqlParameterSource("user_id", userId);
        return namedParameterJdbcTemplate.query(sql, params, ticketMapper);
    }

    @Override
    public List<Ticket> findByScheduleId(int scheduleId) {
        String sql = """
                SELECT *
                FROM tickets
                WHERE schedule_id = :schedule_id
                """;
        MapSqlParameterSource params = new MapSqlParameterSource("schedule_id", scheduleId);
        return namedParameterJdbcTemplate.query(sql, params, ticketMapper);
    }

    @Override
    public List<Ticket> findAll(int page, int size) {
        int offset = 0;
        if (page > 1) {
            offset = (page - 1) * size;
        }
        String sql = """
                SELECT *
                FROM tickets
                ORDER BY id ASC
                LIMIT :limit OFFSET :offset
                """;
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("limit", size)
                .addValue("offset", offset);
        return namedParameterJdbcTemplate.query(sql, params, ticketMapper);
    }

    @Override
    public int count() {
        String sql = """
                SELECT COUNT(*)
                FROM tickets
                """;
        MapSqlParameterSource params = new MapSqlParameterSource();
        Integer count = namedParameterJdbcTemplate.queryForObject(sql, params, Integer.class);
        if (count != null) {
            return count;
        }
        return 0;
    }

    @Override
    public int update(Ticket ticket) {
        String sql = """
                UPDATE tickets
                SET user_id = :user_id, schedule_id = :schedule_id, coach_id = :coach_id,
                seat_number = :seat_number, booking_time = :booking_time, total_amount = :total_amount,
                status = :status, actual_return_timestamp = :actual_return_timestamp, refund_amount = :refund_amount
                WHERE id = :id
                """;
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("user_id", ticket.getUserId())
                .addValue("schedule_id", ticket.getScheduleId())
                .addValue("coach_id", ticket.getCoachId())
                .addValue("seat_number", ticket.getSeatNumber())
                .addValue("booking_time", ticket.getBookingTime())
                .addValue("total_amount", ticket.getTotalAmount())
                .addValue("status", ticket.getStatus().name())
                .addValue("actual_return_timestamp", ticket.getActualReturnTimestamp())
                .addValue("refund_amount", ticket.getRefundAmount())
                .addValue("id", ticket.getId());
        return namedParameterJdbcTemplate.update(sql, params);
    }

    @Override
    public int deleteById(int id) {
        String sql = """
                DELETE
                FROM tickets
                WHERE id = :id
                """;
        MapSqlParameterSource params = new MapSqlParameterSource("id", id);
        return namedParameterJdbcTemplate.update(sql, params);
    }

    @Override
    public int updateTicketStatusAndRefund(int ticketId, String status, float refundAmount, LocalDateTime returnTime) {
        String sql = """
            UPDATE tickets
            SET status = :status, refund_amount = :refund_amount, actual_return_timestamp = :actual_return_timestamp
            WHERE id = :id
            """;
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", ticketId)
                .addValue("status", status)
                .addValue("refund_amount", refundAmount)
                .addValue("actual_return_timestamp", returnTime);
        return namedParameterJdbcTemplate.update(sql, params);
    }

    public boolean isSeatAvailable(int scheduleId, int coachId, String seatNumber) {
        String sql = """
                SELECT (c.capacity - COUNT(t.id)) as available_seats
                FROM coaches c
                LEFT JOIN tickets t ON c.id = t.coach_id
                    AND t.schedule_id = :scheduleId
                    AND t.status IN ('BOOKED', 'CONFIRMED', 'PENDING')
                WHERE c.id = :coachId;
            """;
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("scheduleId", scheduleId)
                .addValue("coachId", coachId)
                .addValue("seatNumber", seatNumber);

        Integer emptySeats = namedParameterJdbcTemplate.queryForObject(sql, params, Integer.class);
        return emptySeats != null && emptySeats > 0;
    }



}

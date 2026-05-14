package com.railway.ticket.management.system.repository.mapper;

import com.railway.ticket.management.system.domain.Ticket;
import com.railway.ticket.management.system.domain.enums.TicketStatus;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class TicketMapper implements RowMapper<Ticket> {

    @Override
    public Ticket mapRow(ResultSet rs, int rowNum) throws SQLException {
        String statusString = rs.getString("status");
        TicketStatus status = null;
        if (statusString != null && !statusString.isEmpty()) {
            status = TicketStatus.valueOf(statusString);
        }

        Timestamp bookingTimestamp = rs.getTimestamp("booking_time");
        LocalDateTime bookingTime = null;
        if (bookingTimestamp != null) {
            bookingTime = bookingTimestamp.toLocalDateTime();
        }

        Timestamp actualReturnTimestamp = rs.getTimestamp("actualReturnTimestamp");
        LocalDateTime actualReturnTime = null;
        if(actualReturnTimestamp != null) {
            actualReturnTime =  actualReturnTimestamp.toLocalDateTime();
        }

        return new Ticket(
                rs.getInt("id"),
                rs.getInt("user_id"),
                rs.getInt("schedule_id"),
                rs.getInt("coach_id"),
                rs.getString("seat_number"),
                bookingTime,
                rs.getFloat("total_amount"),
                status,
                actualReturnTime,
                rs.getFloat("refund_amount")
        );
    }
}


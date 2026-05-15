package com.railway.ticket.management.system.repository.mapper;

import com.railway.ticket.management.system.domain.Schedule;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class ScheduleMapper implements RowMapper<Schedule> {

    @Override
    public Schedule mapRow(ResultSet rs, int rowNum) throws SQLException {
        Timestamp departureTimestamp = rs.getTimestamp("departure_time");
        LocalDateTime departureTime = departureTimestamp != null ? departureTimestamp.toLocalDateTime() : null;

        Timestamp arrivalTimestamp = rs.getTimestamp("arrival_time");
        LocalDateTime arrivalTime = arrivalTimestamp != null ? arrivalTimestamp.toLocalDateTime() : null;

        return new Schedule(
                rs.getInt("id"),
                rs.getInt("train_id"),
                rs.getInt("source_station_id"),
                rs.getInt("destination_station_id"),
                departureTime,
                arrivalTime
        );
    }
}

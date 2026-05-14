package com.railway.ticket.management.system.repository.mapper;

import com.railway.ticket.management.system.domain.Schedule;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class ScheduleMapper implements RowMapper<Schedule> {

    @Override
    public Schedule mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Schedule(
                rs.getInt("id"),
                rs.getInt("train_id"),
                rs.getInt("source_station_id"),
                rs.getInt("destination_station_id"),
                rs.getTimestamp("departure_time").toLocalDateTime(),
                rs.getTimestamp("arrival_time").toLocalDateTime()
        );
    }
}

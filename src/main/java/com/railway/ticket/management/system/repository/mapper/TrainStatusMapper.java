package com.railway.ticket.management.system.repository.mapper;

import com.railway.ticket.management.system.domain.TrainStatus;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class TrainStatusMapper implements RowMapper<TrainStatus> {

    @Override
    public TrainStatus mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new TrainStatus(
                rs.getInt("id"),
                rs.getInt("train_id"),
                rs.getInt("current_station_id"),
                rs.getInt("next_station_id"),
                rs.getString("status"),
                rs.getTimestamp("last_updated").toLocalDateTime()
        );
    }
}

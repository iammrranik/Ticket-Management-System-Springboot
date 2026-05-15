package com.railway.ticket.management.system.repository.mapper;

import com.railway.ticket.management.system.domain.TrainStatus;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class TrainStatusMapper implements RowMapper<TrainStatus> {

    @Override
    public TrainStatus mapRow(ResultSet rs, int rowNum) throws SQLException {
        Timestamp lastUpdatedTimestamp = rs.getTimestamp("last_updated");
        LocalDateTime lastUpdated = lastUpdatedTimestamp != null ? lastUpdatedTimestamp.toLocalDateTime() : null;

        return new TrainStatus(
                rs.getInt("id"),
                rs.getInt("train_id"),
                rs.getInt("current_station_id"),
                rs.getInt("next_station_id"),
                rs.getString("status"),
                lastUpdated
        );
    }
}

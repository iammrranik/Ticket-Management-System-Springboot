package com.railway.ticket.management.system.repository.mapper;

import com.railway.ticket.management.system.domain.FoodOrder;
import com.railway.ticket.management.system.domain.enums.FoodOrderStatus;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class FoodOrderMapper implements RowMapper<FoodOrder> {

    @Override
    public FoodOrder mapRow(ResultSet rs, int rowNum) throws SQLException {
        Timestamp orderTimestamp = rs.getTimestamp("order_timestamp");
        LocalDateTime orderTime = orderTimestamp != null ? orderTimestamp.toLocalDateTime() : null;

        String statusString = rs.getString("status");
        FoodOrderStatus status = statusString != null ? FoodOrderStatus.valueOf(statusString) : null;

        return new FoodOrder(
                rs.getInt("id"),
                rs.getInt("ticket_id"),
                rs.getFloat("total_amount"),
                orderTime,
                status
        );
    }
}

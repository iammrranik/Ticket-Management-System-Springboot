package com.railway.ticket.management.system.repository.mapper;

import com.railway.ticket.management.system.domain.FoodOrder;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class FoodOrderMapper implements RowMapper<FoodOrder> {

    @Override
    public FoodOrder mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new FoodOrder(
                rs.getInt("id"),
                rs.getInt("ticket_id"),
                rs.getFloat("total_amount"),
                rs.getTimestamp("order_timestamp").toLocalDateTime(),
                rs.getString("status")
        );
    }
}

package com.railway.ticket.management.system.repository.mapper;

import com.railway.ticket.management.system.domain.FoodOrderDetails;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FoodOrderDetailsMapper implements RowMapper<FoodOrderDetails> {

    @Override
    public FoodOrderDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new FoodOrderDetails(
                rs.getInt("id"),
                rs.getInt("order_id"),
                rs.getInt("food_item_id"),
                rs.getInt("quantity"),
                rs.getFloat("sub_total")
        );
    }
}

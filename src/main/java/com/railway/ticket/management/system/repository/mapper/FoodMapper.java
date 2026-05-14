package com.railway.ticket.management.system.repository.mapper;

import com.railway.ticket.management.system.domain.FoodItem;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FoodMapper implements RowMapper<FoodItem> {

    @Override
    public FoodItem mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new FoodItem(
                rs.getInt("id"),
                rs.getString("item_name"),
                rs.getString("category"),
                rs.getFloat("price"),
                rs.getInt("available_quantity")
        );
    }
}

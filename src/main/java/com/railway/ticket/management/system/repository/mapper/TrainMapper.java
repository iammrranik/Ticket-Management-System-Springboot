package com.railway.ticket.management.system.repository.mapper;

import com.railway.ticket.management.system.domain.Train;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TrainMapper implements RowMapper<Train> {

    @Override
    public Train mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Train(
                rs.getInt("id"),
                rs.getString("train_registration_number"),
                rs.getString("train_name"),
                rs.getInt("total_coaches"),
                rs.getInt("total_ac_coaches"),
                rs.getInt("total_non_ac_coaches"),
                rs.getInt("total_compartment_coaches")
        );
    }
}

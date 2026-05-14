package com.railway.ticket.management.system.repository.mapper;

import com.railway.ticket.management.system.domain.Station;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StationMapper implements RowMapper<Station> {

    @Override
    public Station mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Station(
                rs.getInt("id"),
                rs.getString("station_code"),
                rs.getString("station_name"),
                rs.getString("city")
        );
    }
}

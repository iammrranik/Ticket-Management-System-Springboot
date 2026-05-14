package com.railway.ticket.management.system.repository.mapper;

import com.railway.ticket.management.system.domain.Coach;
import com.railway.ticket.management.system.domain.enums.CoachType;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CoachMapper implements RowMapper<Coach> {

    @Override
    public Coach mapRow(ResultSet rs, int rowNum) throws SQLException {
        String coachTypeString = rs.getString("coach_type");
        CoachType coachType = null;

        if (coachTypeString != null && !coachTypeString.isEmpty()) {
            coachType = CoachType.valueOf(coachTypeString);
        }

        return new Coach(
                rs.getInt("id"),
                rs.getInt("train_id"),
                coachType,
                rs.getInt("capacity"),
                rs.getFloat("base_fare")
        );
    }
}

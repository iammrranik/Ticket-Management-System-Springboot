package com.railway.ticket.management.system.repository.mapper;

import com.railway.ticket.management.system.domain.ReturnPolicy;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReturnPolicyMapper implements RowMapper<ReturnPolicy> {

    @Override
    public ReturnPolicy mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new ReturnPolicy(
                rs.getInt("id"),
                rs.getString("policy_name"),
                rs.getInt("hours_before_departure"),
                rs.getFloat("deduction_percentage")
        );
    }
}

package com.railway.ticket.management.system.repository.mapper;

import com.railway.ticket.management.system.domain.User;
import com.railway.ticket.management.system.domain.enums.Role;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        String roleString = rs.getString("role");
        Role role = null;

        if (roleString != null && !roleString.isEmpty()) {
            role = Role.valueOf(roleString);
        }

        return new User(
                rs.getInt("id"),
                rs.getString("username"),
                rs.getString("password"),
                rs.getString("email"),
                rs.getString("fullname"),
                role
        );
    }
}

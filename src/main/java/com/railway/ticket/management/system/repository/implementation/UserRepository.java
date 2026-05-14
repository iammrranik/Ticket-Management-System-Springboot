package com.railway.ticket.management.system.repository.implementation;

import com.railway.ticket.management.system.domain.User;
import com.railway.ticket.management.system.repository.IUserRepository;
import com.railway.ticket.management.system.repository.mapper.UserMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class UserRepository implements IUserRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private static final UserMapper userMapper = new UserMapper();

    public UserRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public int save(User user) {
        String sql = """
                INSERT INTO users (username, password, email, fullname, role)
                VALUES (:username, :password, :email, :fullname, :role)
                """;
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("username", user.getUsername())
                .addValue("password", user.getPassword())
                .addValue("email", user.getEmail())
                .addValue("fullname", user.getFullName())
                .addValue("role", user.getRole().name());
        return namedParameterJdbcTemplate.update(sql, params);
    }

    @Override
    public Optional<User> findById(int id) {
        String sql = """
                SELECT *
                FROM users
                WHERE id = :id
                """;
        MapSqlParameterSource params = new MapSqlParameterSource("id", id);
        try{
            User user = namedParameterJdbcTemplate.queryForObject(sql, params, userMapper);
            return Optional.ofNullable(user);
        }catch(Exception e){
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> findByUsername(String username) {
        String sql = """
                SELECT *
                FROM users
                WHERE username = :username
                """;
        MapSqlParameterSource params = new MapSqlParameterSource("username", username);
        try{
            User user = namedParameterJdbcTemplate.queryForObject(sql, params, userMapper);
            return Optional.ofNullable(user);
        } catch(Exception e){
            return Optional.empty();
        }
    }

    @Override
    public List<User> findAll(int page, int size) {
        int offset = 0;
        if(page > 1){
            offset = (page - 1) * size;
        }
        String sql = """
                SELECT *
                FROM users
                ORDER BY id ASC
                LIMIT :limit OFFSET :offset
                """;
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("limit", size)
                .addValue("offset", offset);
        return namedParameterJdbcTemplate.query(sql, params, userMapper);
    }

    @Override
    public int count() {
        String sql = """
                SELECT COUNT(*)
                FROM users
                """;
        MapSqlParameterSource params = new MapSqlParameterSource();
        Integer count = namedParameterJdbcTemplate.queryForObject(sql, params, Integer.class);
        if (count != null) {
            return count;
        }
        return 0;
    }

    @Override
    public int update(User user) {
        String sql = """
                UPDATE users
                SET username = :username, password = :password,
                email = :email, fullname = :fullname, role = :role
                WHERE id = :id
                """;
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("username", user.getUsername())
                .addValue("password", user.getPassword())
                .addValue("email", user.getEmail())
                .addValue("fullname", user.getFullName())
                .addValue("role", user.getRole().name())
                .addValue("id", user.getId());
        return namedParameterJdbcTemplate.update(sql, params);
    }

    @Override
    public int deleteById(int id) {
        String sql = """
                DELETE
                FROM users
                WHERE id = :id
                """;
        MapSqlParameterSource params = new MapSqlParameterSource("id", id);
        return namedParameterJdbcTemplate.update(sql, params);
    }

}

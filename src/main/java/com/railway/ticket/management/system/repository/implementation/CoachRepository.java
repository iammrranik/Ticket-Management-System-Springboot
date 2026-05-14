package com.railway.ticket.management.system.repository.implementation;

import com.railway.ticket.management.system.domain.Coach;
import com.railway.ticket.management.system.repository.ICoachRepository;
import com.railway.ticket.management.system.repository.mapper.CoachMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CoachRepository implements ICoachRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private static final CoachMapper coachMapper = new CoachMapper();

    public CoachRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public int save(Coach coach) {
        String sql = """
                INSERT INTO coaches (train_id, coach_type, capacity, base_fare)
                VALUES (:train_id, :coach_type, :capacity, :base_fare)
                """;
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("train_id", coach.getTrainId())
                .addValue("coach_type", coach.getCoachType().name())
                .addValue("capacity", coach.getCapacity())
                .addValue("base_fare", coach.getBaseFare());
        return namedParameterJdbcTemplate.update(sql, params);
    }

    @Override
    public Optional<Coach> findById(int id) {
        String sql = """
                SELECT *
                FROM coaches
                WHERE id = :id
                """;
        MapSqlParameterSource params = new MapSqlParameterSource("id", id);
        try {
            Coach coach = namedParameterJdbcTemplate.queryForObject(sql, params, coachMapper);
            return Optional.ofNullable(coach);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Coach> findByTrainId(int trainId) {
        String sql = """
                SELECT *
                FROM coaches
                WHERE train_id = :train_id
                """;
        MapSqlParameterSource params = new MapSqlParameterSource("train_id", trainId);
        return namedParameterJdbcTemplate.query(sql, params, coachMapper);
    }

    @Override
    public List<Coach> findAll(int page, int size) {
        int offset = 0;
        if (page > 1) {
            offset = (page - 1) * size;
        }
        String sql = """
                SELECT *
                FROM coaches
                ORDER BY id ASC
                LIMIT :limit OFFSET :offset
                """;
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("limit", size)
                .addValue("offset", offset);
        return namedParameterJdbcTemplate.query(sql, params, coachMapper);
    }

    @Override
    public int count() {
        String sql = """
                SELECT COUNT(*)
                FROM coaches
                """;
        MapSqlParameterSource params = new MapSqlParameterSource();
        Integer count = namedParameterJdbcTemplate.queryForObject(sql, params, Integer.class);
        if (count != null) {
            return count;
        }
        return 0;
    }

    @Override
    public int update(Coach coach) {
        String sql = """
                UPDATE coaches
                SET train_id = :train_id, coach_type = :coach_type,
                capacity = :capacity, base_fare = :base_fare
                WHERE id = :id
                """;
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("train_id", coach.getTrainId())
                .addValue("coach_type", coach.getCoachType().name())
                .addValue("capacity", coach.getCapacity())
                .addValue("base_fare", coach.getBaseFare())
                .addValue("id", coach.getId());
        return namedParameterJdbcTemplate.update(sql, params);
    }

    @Override
    public int deleteById(int id) {
        String sql = """
                DELETE
                FROM coaches
                WHERE id = :id
                """;
        MapSqlParameterSource params = new MapSqlParameterSource("id", id);
        return namedParameterJdbcTemplate.update(sql, params);
    }

    @Override
    public int updateCoachBaseFare(int coachId, float newFare) {
        String sql = """
            UPDATE coaches
            SET base_fare = :base_fare
            WHERE id = :id
            """;
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", coachId)
                .addValue("base_fare", newFare);
        return namedParameterJdbcTemplate.update(sql, params);
    }
}

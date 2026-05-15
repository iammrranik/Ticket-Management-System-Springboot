package com.railway.ticket.management.system.repository.implementation;

import com.railway.ticket.management.system.domain.ReturnPolicy;
import com.railway.ticket.management.system.repository.IReturnPolicyRepository;
import com.railway.ticket.management.system.repository.mapper.ReturnPolicyMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ReturnPolicyRepository implements IReturnPolicyRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private static final ReturnPolicyMapper returnPolicyMapper = new ReturnPolicyMapper();

    public ReturnPolicyRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public int save(ReturnPolicy returnPolicy) {
        String sql = """
                INSERT INTO return_policies (policy_name, hours_before_departure, deduction_percentage)
                VALUES (:policy_name, :hours_before_departure, :deduction_percentage)
                """;
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("policy_name", returnPolicy.getPolicyName())
                .addValue("hours_before_departure", returnPolicy.getHoursBeforeDeparture())
                .addValue("deduction_percentage", returnPolicy.getDeductionPercentage());
        return namedParameterJdbcTemplate.update(sql, params);
    }

    @Override
    public Optional<ReturnPolicy> findById(int id) {
        String sql = """
                SELECT *
                FROM return_policies
                WHERE id = :id
                """;
        MapSqlParameterSource params = new MapSqlParameterSource("id", id);
        try {
            ReturnPolicy returnPolicy = namedParameterJdbcTemplate.queryForObject(sql, params, returnPolicyMapper);
            return Optional.ofNullable(returnPolicy);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public List<ReturnPolicy> findAll(int page, int size) {
        int offset = 0;
        if (page > 1) {
            offset = (page - 1) * size;
        }
        String sql = """
                SELECT *
                FROM return_policies
                ORDER BY id ASC
                LIMIT :limit OFFSET :offset
                """;
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("limit", size)
                .addValue("offset", offset);
        return namedParameterJdbcTemplate.query(sql, params, returnPolicyMapper);
    }

    @Override
    public List<ReturnPolicy> getAll() {
        String sql = """
                SELECT *
                FROM return_policies
                ORDER BY hours_before_departure DESC
                """;
        MapSqlParameterSource params = new MapSqlParameterSource();
        return namedParameterJdbcTemplate.query(sql, params, returnPolicyMapper);

    }

    @Override
    public int count() {
        String sql = """
                SELECT COUNT(*)
                FROM return_policies
                """;
        MapSqlParameterSource params = new MapSqlParameterSource();
        Integer count = namedParameterJdbcTemplate.queryForObject(sql, params, Integer.class);
        if (count != null) {
            return count;
        }
        return 0;
    }

    @Override
    public int update(ReturnPolicy returnPolicy) {
        String sql = """
                UPDATE return_policies
                SET policy_name = :policy_name, hours_before_departure = :hours_before_departure,
                deduction_percentage = :deduction_percentage
                WHERE id = :id
                """;
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("policy_name", returnPolicy.getPolicyName())
                .addValue("hours_before_departure", returnPolicy.getHoursBeforeDeparture())
                .addValue("deduction_percentage", returnPolicy.getDeductionPercentage())
                .addValue("id", returnPolicy.getId());
        return namedParameterJdbcTemplate.update(sql, params);
    }

    @Override
    public int deleteById(int id) {
        String sql = """
                DELETE
                FROM return_policies
                WHERE id = :id
                """;
        MapSqlParameterSource params = new MapSqlParameterSource("id", id);
        return namedParameterJdbcTemplate.update(sql, params);
    }
}

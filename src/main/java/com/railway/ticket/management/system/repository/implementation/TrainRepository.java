package com.railway.ticket.management.system.repository.implementation;

import com.railway.ticket.management.system.domain.Train;
import com.railway.ticket.management.system.repository.ITrainRepository;
import com.railway.ticket.management.system.repository.mapper.TrainMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TrainRepository implements ITrainRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private static final TrainMapper trainMapper = new TrainMapper();

    public TrainRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public int save(Train train) {
        String sql = """
                INSERT INTO trains (train_registration_number, train_name, total_coaches, total_ac_coaches, total_non_ac_coaches, total_compartment_coaches)
                VALUES (:train_registration_number, :train_name, :total_coaches, :total_ac_coaches, :total_non_ac_coaches, :total_compartment_coaches)
                """;
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("train_registration_number", train.getTrainRegistrationNumber())
                .addValue("train_name", train.getTrainName())
                .addValue("total_coaches", train.getTotalCoaches())
                .addValue("total_ac_coaches", train.getTotalAcCoaches())
                .addValue("total_non_ac_coaches", train.getTotalNonAcCoaches())
                .addValue("total_compartment_coaches", train.getTotalCompartmentCoaches());
        return namedParameterJdbcTemplate.update(sql, params);
    }

    @Override
    public Optional<Train> findById(int id) {
        String sql = """
                SELECT *
                FROM trains
                WHERE id = :id
                """;
        MapSqlParameterSource params = new MapSqlParameterSource("id", id);
        try {
            Train train = namedParameterJdbcTemplate.queryForObject(sql, params, trainMapper);
            return Optional.ofNullable(train);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Train> findByTrainRegistrationNumber(String trainRegistrationNumber) {
        String sql = """
                SELECT *
                FROM trains
                WHERE train_registration_number = :train_registration_number
                """;
        MapSqlParameterSource params = new MapSqlParameterSource("train_registration_number", trainRegistrationNumber);
        try {
            Train train = namedParameterJdbcTemplate.queryForObject(sql, params, trainMapper);
            return Optional.ofNullable(train);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Train> findAll(int page, int size) {
        int offset = 0;
        if (page > 1) {
            offset = (page - 1) * size;
        }
        String sql = """
                SELECT *
                FROM trains
                ORDER BY id ASC
                LIMIT :limit OFFSET :offset
                """;
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("limit", size)
                .addValue("offset", offset);
        return namedParameterJdbcTemplate.query(sql, params, trainMapper);
    }

    @Override
    public int count() {
        String sql = """
                SELECT COUNT(*)
                FROM trains
                """;
        MapSqlParameterSource params = new MapSqlParameterSource();
        Integer count = namedParameterJdbcTemplate.queryForObject(sql, params, Integer.class);
        if (count != null) {
            return count;
        }
        return 0;
    }

    @Override
    public int update(Train train) {
        String sql = """
                UPDATE trains
                SET train_registration_number = :train_registration_number, train_name = :train_name,
                total_coaches = :total_coaches, total_ac_coaches = :total_ac_coaches,
                total_non_ac_coaches = :total_non_ac_coaches, total_compartment_coaches = :total_compartment_coaches
                WHERE id = :id
                """;
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("train_registration_number", train.getTrainRegistrationNumber())
                .addValue("train_name", train.getTrainName())
                .addValue("total_coaches", train.getTotalCoaches())
                .addValue("total_ac_coaches", train.getTotalAcCoaches())
                .addValue("total_non_ac_coaches", train.getTotalNonAcCoaches())
                .addValue("total_compartment_coaches", train.getTotalCompartmentCoaches())
                .addValue("id", train.getId());
        return namedParameterJdbcTemplate.update(sql, params);
    }

    @Override
    public int deleteById(int id) {
        String sql = """
                DELETE
                FROM trains
                WHERE id = :id
                """;
        MapSqlParameterSource params = new MapSqlParameterSource("id", id);
        return namedParameterJdbcTemplate.update(sql, params);
    }
}

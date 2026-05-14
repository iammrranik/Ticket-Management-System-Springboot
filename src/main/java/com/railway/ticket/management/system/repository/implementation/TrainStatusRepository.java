package com.railway.ticket.management.system.repository.implementation;

import com.railway.ticket.management.system.domain.TrainStatus;
import com.railway.ticket.management.system.repository.ITrainStatusRepository;
import com.railway.ticket.management.system.repository.mapper.TrainStatusMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class TrainStatusRepository implements ITrainStatusRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private static final TrainStatusMapper trainStatusMapper = new TrainStatusMapper();

    public TrainStatusRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public int save(TrainStatus trainStatus) {
        String sql = """
                INSERT INTO train_status (train_id, current_station_id, next_station_id, status, last_updated)
                VALUES (:train_id, :current_station_id, :next_station_id, :status, :last_updated)
                """;
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("train_id", trainStatus.getTrainId())
                .addValue("current_station_id", trainStatus.getCurrentStationId())
                .addValue("next_station_id", trainStatus.getNextStationId())
                .addValue("status", trainStatus.getStatus())
                .addValue("last_updated", trainStatus.getLastUpdated());
        return namedParameterJdbcTemplate.update(sql, params);
    }

    @Override
    public Optional<TrainStatus> findById(int id) {
        String sql = """
                SELECT *
                FROM train_status
                WHERE id = :id
                """;
        MapSqlParameterSource params = new MapSqlParameterSource("id", id);
        try {
            TrainStatus trainStatus = namedParameterJdbcTemplate.queryForObject(sql, params, trainStatusMapper);
            return Optional.ofNullable(trainStatus);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public List<TrainStatus> findByTrainId(int trainId) {
        String sql = """
                SELECT *
                FROM train_status
                WHERE train_id = :train_id
                """;
        MapSqlParameterSource params = new MapSqlParameterSource("train_id", trainId);
        return namedParameterJdbcTemplate.query(sql, params, trainStatusMapper);
    }

    @Override
    public List<TrainStatus> findAll(int page, int size) {
        int offset = 0;
        if (page > 1) {
            offset = (page - 1) * size;
        }
        String sql = """
                SELECT *
                FROM train_status
                ORDER BY id ASC
                LIMIT :limit OFFSET :offset
                """;
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("limit", size)
                .addValue("offset", offset);
        return namedParameterJdbcTemplate.query(sql, params, trainStatusMapper);
    }

    @Override
    public int count() {
        String sql = """
                SELECT COUNT(*)
                FROM train_status
                """;
        MapSqlParameterSource params = new MapSqlParameterSource();
        Integer count = namedParameterJdbcTemplate.queryForObject(sql, params, Integer.class);
        if (count != null) {
            return count;
        }
        return 0;
    }

    @Override
    public int update(TrainStatus trainStatus) {
        String sql = """
                UPDATE train_status
                SET train_id = :train_id, current_station_id = :current_station_id,
                next_station_id = :next_station_id, status = :status, last_updated = :last_updated
                WHERE id = :id
                """;
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("train_id", trainStatus.getTrainId())
                .addValue("current_station_id", trainStatus.getCurrentStationId())
                .addValue("next_station_id", trainStatus.getNextStationId())
                .addValue("status", trainStatus.getStatus())
                .addValue("last_updated", trainStatus.getLastUpdated())
                .addValue("id", trainStatus.getId());
        return namedParameterJdbcTemplate.update(sql, params);
    }

    @Override
    public int deleteById(int id) {
        String sql = """
                DELETE
                FROM train_status
                WHERE id = :id
                """;
        MapSqlParameterSource params = new MapSqlParameterSource("id", id);
        return namedParameterJdbcTemplate.update(sql, params);
    }

    @Override
    public int updateTrainLocation(int trainId, int currentStationId, int nextStationId, String status) {
        String sql = """
            UPDATE train_status
            SET current_station_id = :current_station_id,
                next_station_id = :next_station_id,
                status = :status,
                last_updated = :last_updated
            WHERE train_id = :train_id
            """;
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("train_id", trainId)
                .addValue("current_station_id", currentStationId)
                .addValue("next_station_id", nextStationId)
                .addValue("status", status)
                .addValue("last_updated", LocalDateTime.now());
        return namedParameterJdbcTemplate.update(sql, params);
    }
}

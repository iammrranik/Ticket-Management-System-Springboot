package com.railway.ticket.management.system.repository.implementation;

import com.railway.ticket.management.system.domain.Schedule;
import com.railway.ticket.management.system.repository.IScheduleRepository;
import com.railway.ticket.management.system.repository.mapper.ScheduleMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class ScheduleRepository implements IScheduleRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private static final ScheduleMapper scheduleMapper = new ScheduleMapper();

    public ScheduleRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public int save(Schedule schedule) {
        String sql = """
                INSERT INTO schedules (train_id, source_station_id, destination_station_id, departure_time, arrival_time)
                VALUES (:train_id, :source_station_id, :destination_station_id, :departure_time, :arrival_time)
                """;
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("train_id", schedule.getTrainId())
                .addValue("source_station_id", schedule.getSourceStationId())
                .addValue("destination_station_id", schedule.getDestinationStationId())
                .addValue("departure_time", schedule.getDepartureTime())
                .addValue("arrival_time", schedule.getArrivalTime());
        return namedParameterJdbcTemplate.update(sql, params);
    }

    @Override
    public Optional<Schedule> findById(int id) {
        String sql = """
                SELECT *
                FROM schedules
                WHERE id = :id
                """;
        MapSqlParameterSource params = new MapSqlParameterSource("id", id);
        try {
            Schedule schedule = namedParameterJdbcTemplate.queryForObject(sql, params, scheduleMapper);
            return Optional.ofNullable(schedule);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Schedule> findByTrainId(int trainId) {
        String sql = """
                SELECT *
                FROM schedules
                WHERE train_id = :train_id
                """;
        MapSqlParameterSource params = new MapSqlParameterSource("train_id", trainId);
        return namedParameterJdbcTemplate.query(sql, params, scheduleMapper);
    }

    @Override
    public List<Schedule> findBySourceStationId(int sourceStationId) {
        String sql = """
                SELECT *
                FROM schedules
                WHERE source_station_id = :source_station_id
                """;
        MapSqlParameterSource params = new MapSqlParameterSource("source_station_id", sourceStationId);
        return namedParameterJdbcTemplate.query(sql, params, scheduleMapper);
    }

    @Override
    public List<Schedule> findByDestinationStationId(int destinationStationId) {
        String sql = """
                SELECT *
                FROM schedules
                WHERE destination_station_id = :destination_station_id
                """;
        MapSqlParameterSource params = new MapSqlParameterSource("destination_station_id", destinationStationId);
        return namedParameterJdbcTemplate.query(sql, params, scheduleMapper);
    }

    @Override
    public List<Schedule> findAll(int page, int size) {
        int offset = 0;
        if (page > 1) {
            offset = (page - 1) * size;
        }
        String sql = """
                SELECT *
                FROM schedules
                ORDER BY id ASC
                LIMIT :limit OFFSET :offset
                """;
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("limit", size)
                .addValue("offset", offset);
        return namedParameterJdbcTemplate.query(sql, params, scheduleMapper);
    }

    @Override
    public int count() {
        String sql = """
                SELECT COUNT(*)
                FROM schedules
                """;
        MapSqlParameterSource params = new MapSqlParameterSource();
        Integer count = namedParameterJdbcTemplate.queryForObject(sql, params, Integer.class);
        if (count != null) {
            return count;
        }
        return 0;
    }

    @Override
    public int update(Schedule schedule) {
        String sql = """
                UPDATE schedules
                SET train_id = :train_id, source_station_id = :source_station_id,
                destination_station_id = :destination_station_id, departure_time = :departure_time,
                arrival_time = :arrival_time
                WHERE id = :id
                """;
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("train_id", schedule.getTrainId())
                .addValue("source_station_id", schedule.getSourceStationId())
                .addValue("destination_station_id", schedule.getDestinationStationId())
                .addValue("departure_time", schedule.getDepartureTime())
                .addValue("arrival_time", schedule.getArrivalTime())
                .addValue("id", schedule.getId());
        return namedParameterJdbcTemplate.update(sql, params);
    }

    @Override
    public int deleteById(int id) {
        String sql = """
                DELETE
                FROM schedules
                WHERE id = :id
                """;
        MapSqlParameterSource params = new MapSqlParameterSource("id", id);
        return namedParameterJdbcTemplate.update(sql, params);
    }

    @Override
    public int updateScheduleTime(int scheduleId, LocalDateTime newDeparture, LocalDateTime newArrival) {
        String sql = """
            UPDATE schedules
            SET departure_time = :departure_time,
                arrival_time = :arrival_time
            WHERE id = :id
            """;
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", scheduleId)
                .addValue("departure_time", newDeparture)
                .addValue("arrival_time", newArrival);
        return namedParameterJdbcTemplate.update(sql, params);
    }


}

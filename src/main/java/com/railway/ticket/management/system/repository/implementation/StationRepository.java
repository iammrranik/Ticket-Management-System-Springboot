package com.railway.ticket.management.system.repository.implementation;

import com.railway.ticket.management.system.domain.Station;
import com.railway.ticket.management.system.repository.IStationRepository;
import com.railway.ticket.management.system.repository.mapper.StationMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class StationRepository implements IStationRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private static final StationMapper stationMapper = new StationMapper();

    public StationRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public int save(Station station) {
        String sql = """
                INSERT INTO stations (station_code, station_name, city)
                VALUES (:station_code, :station_name, :city)
                """;
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("station_code", station.getStationCode())
                .addValue("station_name", station.getStationName())
                .addValue("city", station.getCity());
        return namedParameterJdbcTemplate.update(sql, params);
    }

    @Override
    public Optional<Station> findById(int id) {
        String sql = """
                SELECT *
                FROM stations
                WHERE id = :id
                """;
        MapSqlParameterSource params = new MapSqlParameterSource("id", id);
        try {
            Station station = namedParameterJdbcTemplate.queryForObject(sql, params, stationMapper);
            return Optional.ofNullable(station);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Station> findByStationCode(String stationCode) {
        String sql = """
                SELECT *
                FROM stations
                WHERE station_code = :station_code
                """;
        MapSqlParameterSource params = new MapSqlParameterSource("station_code", stationCode);
        try {
            Station station = namedParameterJdbcTemplate.queryForObject(sql, params, stationMapper);
            return Optional.ofNullable(station);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Station> findAll(int page, int size) {
        int offset = 0;
        if (page > 1) {
            offset = (page - 1) * size;
        }
        String sql = """
                SELECT *
                FROM stations
                ORDER BY id ASC
                LIMIT :limit OFFSET :offset
                """;
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("limit", size)
                .addValue("offset", offset);
        return namedParameterJdbcTemplate.query(sql, params, stationMapper);
    }

    @Override
    public int count() {
        String sql = """
                SELECT COUNT(*)
                FROM stations
                """;
        MapSqlParameterSource params = new MapSqlParameterSource();
        Integer count = namedParameterJdbcTemplate.queryForObject(sql, params, Integer.class);
        if (count != null) {
            return count;
        }
        return 0;
    }

    @Override
    public int update(Station station) {
        String sql = """
                UPDATE stations
                SET station_code = :station_code, station_name = :station_name, city = :city
                WHERE id = :id
                """;
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("station_code", station.getStationCode())
                .addValue("station_name", station.getStationName())
                .addValue("city", station.getCity())
                .addValue("id", station.getId());
        return namedParameterJdbcTemplate.update(sql, params);
    }

    @Override
    public int deleteById(int id) {
        String sql = """
                DELETE
                FROM stations
                WHERE id = :id
                """;
        MapSqlParameterSource params = new MapSqlParameterSource("id", id);
        return namedParameterJdbcTemplate.update(sql, params);
    }
}

package com.railway.ticket.management.system.repository.implementation;

import com.railway.ticket.management.system.domain.FoodOrder;
import com.railway.ticket.management.system.domain.enums.FoodOrderStatus;
import com.railway.ticket.management.system.repository.IFoodOrderRepository;
import com.railway.ticket.management.system.repository.mapper.FoodOrderMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class FoodOrderRepository implements IFoodOrderRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private static final FoodOrderMapper foodOrderMapper = new FoodOrderMapper();

    public FoodOrderRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public int save(FoodOrder foodOrder) {
        String sql = """
                INSERT INTO food_orders (ticket_id, total_amount, order_timestamp, status)
                VALUES (:ticket_id, :total_amount, :order_timestamp, :status)
                """;
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("ticket_id", foodOrder.getTicketId())
                .addValue("total_amount", foodOrder.getTotalAmount())
                .addValue("order_timestamp", foodOrder.getOrderTimestamp())
                .addValue("status", foodOrder.getStatus().name());
        return namedParameterJdbcTemplate.update(sql, params);
    }

    @Override
    public Optional<FoodOrder> findById(int id) {
        String sql = """
                SELECT *
                FROM food_orders
                WHERE id = :id
                """;
        MapSqlParameterSource params = new MapSqlParameterSource("id", id);
        try {
            FoodOrder foodOrder = namedParameterJdbcTemplate.queryForObject(sql, params, foodOrderMapper);
            return Optional.ofNullable(foodOrder);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public List<FoodOrder> findByTicketId(int ticketId) {
        String sql = """
                SELECT *
                FROM food_orders
                WHERE ticket_id = :ticket_id
                """;
        MapSqlParameterSource params = new MapSqlParameterSource("ticket_id", ticketId);
        return namedParameterJdbcTemplate.query(sql, params, foodOrderMapper);
    }

    @Override
    public List<FoodOrder> findAll(int page, int size) {
        int offset = 0;
        if (page > 1) {
            offset = (page - 1) * size;
        }
        String sql = """
                SELECT *
                FROM food_orders
                ORDER BY id ASC
                LIMIT :limit OFFSET :offset
                """;
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("limit", size)
                .addValue("offset", offset);
        return namedParameterJdbcTemplate.query(sql, params, foodOrderMapper);
    }

    @Override
    public int count() {
        String sql = """
                SELECT COUNT(*)
                FROM food_orders
                """;
        MapSqlParameterSource params = new MapSqlParameterSource();
        Integer count = namedParameterJdbcTemplate.queryForObject(sql, params, Integer.class);
        if (count != null) {
            return count;
        }
        return 0;
    }

    @Override
    public int update(FoodOrder foodOrder) {
        String sql = """
                UPDATE food_orders
                SET ticket_id = :ticket_id, total_amount = :total_amount,
                order_timestamp = :order_timestamp, status = :status
                WHERE id = :id
                """;
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("ticket_id", foodOrder.getTicketId())
                .addValue("total_amount", foodOrder.getTotalAmount())
                .addValue("order_timestamp", foodOrder.getOrderTimestamp())
                .addValue("status", foodOrder.getStatus().name())
                .addValue("id", foodOrder.getId());
        return namedParameterJdbcTemplate.update(sql, params);
    }

    @Override
    public int deleteById(int id) {
        String sql = """
                DELETE
                FROM food_orders
                WHERE id = :id
                """;
        MapSqlParameterSource params = new MapSqlParameterSource("id", id);
        return namedParameterJdbcTemplate.update(sql, params);
    }

    @Override
    public int updateFoodOrderStatus(int orderId, FoodOrderStatus status) {
        String sql = """
            UPDATE food_orders
            SET status = :status
            WHERE id = :id
            """;
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", orderId)
                .addValue("status", status.name());
        return namedParameterJdbcTemplate.update(sql, params);
    }


}

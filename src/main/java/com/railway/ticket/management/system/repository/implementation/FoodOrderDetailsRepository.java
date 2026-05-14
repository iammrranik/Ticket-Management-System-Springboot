package com.railway.ticket.management.system.repository.implementation;

import com.railway.ticket.management.system.domain.FoodOrderDetails;
import com.railway.ticket.management.system.repository.IFoodOrderDetailsRepository;
import com.railway.ticket.management.system.repository.mapper.FoodOrderDetailsMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class FoodOrderDetailsRepository implements IFoodOrderDetailsRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private static final FoodOrderDetailsMapper foodOrderDetailsMapper = new FoodOrderDetailsMapper();

    public FoodOrderDetailsRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public int save(FoodOrderDetails foodOrderDetails) {
        String sql = """
                INSERT INTO food_order_details (order_id, food_item_id, quantity, sub_total)
                VALUES (:order_id, :food_item_id, :quantity, :sub_total)
                """;
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("order_id", foodOrderDetails.getOrderId())
                .addValue("food_item_id", foodOrderDetails.getFoodItemId())
                .addValue("quantity", foodOrderDetails.getQuantity())
                .addValue("sub_total", foodOrderDetails.getSubTotal());
        return namedParameterJdbcTemplate.update(sql, params);
    }

    @Override
    public Optional<FoodOrderDetails> findById(int id) {
        String sql = """
                SELECT *
                FROM food_order_details
                WHERE id = :id
                """;
        MapSqlParameterSource params = new MapSqlParameterSource("id", id);
        try {
            FoodOrderDetails foodOrderDetails = namedParameterJdbcTemplate.queryForObject(sql, params, foodOrderDetailsMapper);
            return Optional.ofNullable(foodOrderDetails);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public List<FoodOrderDetails> findByOrderId(int orderId) {
        String sql = """
                SELECT *
                FROM food_order_details
                WHERE order_id = :order_id
                """;
        MapSqlParameterSource params = new MapSqlParameterSource("order_id", orderId);
        return namedParameterJdbcTemplate.query(sql, params, foodOrderDetailsMapper);
    }

    @Override
    public List<FoodOrderDetails> findAll(int page, int size) {
        int offset = 0;
        if (page > 1) {
            offset = (page - 1) * size;
        }
        String sql = """
                SELECT *
                FROM food_order_details
                ORDER BY id ASC
                LIMIT :limit OFFSET :offset
                """;
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("limit", size)
                .addValue("offset", offset);
        return namedParameterJdbcTemplate.query(sql, params, foodOrderDetailsMapper);
    }

    @Override
    public int count() {
        String sql = """
                SELECT COUNT(*)
                FROM food_order_details
                """;
        MapSqlParameterSource params = new MapSqlParameterSource();
        Integer count = namedParameterJdbcTemplate.queryForObject(sql, params, Integer.class);
        if (count != null) {
            return count;
        }
        return 0;
    }

    @Override
    public int update(FoodOrderDetails foodOrderDetails) {
        String sql = """
                UPDATE food_order_details
                SET order_id = :order_id, food_item_id = :food_item_id,
                quantity = :quantity, sub_total = :sub_total
                WHERE id = :id
                """;
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("order_id", foodOrderDetails.getOrderId())
                .addValue("food_item_id", foodOrderDetails.getFoodItemId())
                .addValue("quantity", foodOrderDetails.getQuantity())
                .addValue("sub_total", foodOrderDetails.getSubTotal())
                .addValue("id", foodOrderDetails.getId());
        return namedParameterJdbcTemplate.update(sql, params);
    }

    @Override
    public int deleteById(int id) {
        String sql = """
                DELETE
                FROM food_order_details
                WHERE id = :id
                """;
        MapSqlParameterSource params = new MapSqlParameterSource("id", id);
        return namedParameterJdbcTemplate.update(sql, params);
    }
}

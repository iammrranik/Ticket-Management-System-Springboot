package com.railway.ticket.management.system.repository.implementation;

import com.railway.ticket.management.system.domain.FoodItem;
import com.railway.ticket.management.system.repository.IFoodItemRepository;
import com.railway.ticket.management.system.repository.mapper.FoodMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class FoodItemRepository implements IFoodItemRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private static final FoodMapper foodMapper = new FoodMapper();

    public FoodItemRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public int save(FoodItem foodItem) {
        String sql = """
                INSERT INTO food_items (item_name, category, price, available_quantity)
                VALUES (:item_name, :category, :price, :available_quantity)
                """;
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("item_name", foodItem.getItemName())
                .addValue("category", foodItem.getCategory())
                .addValue("price", foodItem.getPrice())
                .addValue("available_quantity", foodItem.getAvailableQuantity());
        return namedParameterJdbcTemplate.update(sql, params);
    }

    @Override
    public Optional<FoodItem> findById(int id) {
        String sql = """
                SELECT *
                FROM food_items
                WHERE id = :id
                """;
        MapSqlParameterSource params = new MapSqlParameterSource("id", id);
        try {
            FoodItem foodItem = namedParameterJdbcTemplate.queryForObject(sql, params, foodMapper);
            return Optional.ofNullable(foodItem);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public List<FoodItem> findByCategory(String category) {
        String sql = """
                SELECT *
                FROM food_items
                WHERE category = :category
                """;
        MapSqlParameterSource params = new MapSqlParameterSource("category", category);
        return namedParameterJdbcTemplate.query(sql, params, foodMapper);
    }

    @Override
    public List<FoodItem> findAll(int page, int size) {
        int offset = 0;
        if (page > 1) {
            offset = (page - 1) * size;
        }
        String sql = """
                SELECT *
                FROM food_items
                ORDER BY id ASC
                LIMIT :limit OFFSET :offset
                """;
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("limit", size)
                .addValue("offset", offset);
        return namedParameterJdbcTemplate.query(sql, params, foodMapper);
    }

    @Override
    public int count() {
        String sql = """
                SELECT COUNT(*)
                FROM food_items
                """;
        MapSqlParameterSource params = new MapSqlParameterSource();
        Integer count = namedParameterJdbcTemplate.queryForObject(sql, params, Integer.class);
        if (count != null) {
            return count;
        }
        return 0;
    }

    @Override
    public int update(FoodItem foodItem) {
        String sql = """
                UPDATE food_items
                SET item_name = :item_name, category = :category,
                price = :price, available_quantity = :available_quantity
                WHERE id = :id
                """;
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("item_name", foodItem.getItemName())
                .addValue("category", foodItem.getCategory())
                .addValue("price", foodItem.getPrice())
                .addValue("available_quantity", foodItem.getAvailableQuantity())
                .addValue("id", foodItem.getId());
        return namedParameterJdbcTemplate.update(sql, params);
    }

    @Override
    public int deleteById(int id) {
        String sql = """
                DELETE
                FROM food_items
                WHERE id = :id
                """;
        MapSqlParameterSource params = new MapSqlParameterSource("id", id);
        return namedParameterJdbcTemplate.update(sql, params);
    }

    @Override
    public int updateFoodItemAvailableQuantity(int foodItemId, int quantity) {
        String sql = """
                UPDATE food_items
                SET available_quantity = :available_quantity
                WHERE id = :id
                """;
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", foodItemId)
                .addValue("available_quantity", quantity);
        return namedParameterJdbcTemplate.update(sql, params);
    }

    @Override
    public int deductFoodItemQuantity(int foodItemId, int quantity) {
        String sql = """
                UPDATE food_items
                SET available_quantity = available_quantity - :quantity
                WHERE id = :id AND available_quantity >= :quantity
                """;
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", foodItemId)
                .addValue("quantity", quantity);
        return namedParameterJdbcTemplate.update(sql, params);
    }

    @Override
    public boolean isFoodAvailable(int foodItemId, int requestedQuantity) {
        String sql = """
                SELECT available_quantity
                FROM food_items
                WHERE id = :id
            """;
        MapSqlParameterSource params = new MapSqlParameterSource("id", foodItemId);

        Integer currentStock = namedParameterJdbcTemplate.queryForObject(sql, params, Integer.class);
        return currentStock != null && currentStock >= requestedQuantity;
    }


}

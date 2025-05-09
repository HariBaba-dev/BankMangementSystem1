package com.example.crud.operation.Repository;

import com.example.crud.operation.entity.Item;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Repository
public class ItemRepositoryImpl implements ItemRepository {

	private final JdbcTemplate jdbcTemplate;

	public ItemRepositoryImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public Item save(Item item) {
		if (item.getId() == null) {
			String insertSql = "INSERT INTO item (item_name, price, stock) VALUES (?, ?, ?)";
			KeyHolder keyHolder = new GeneratedKeyHolder();

			jdbcTemplate.update(connection -> {
				PreparedStatement ps = connection.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, item.getItemName());
				ps.setDouble(2, item.getPrice());
				ps.setInt(3, item.getStock());
				return ps;
			}, keyHolder);

			if (keyHolder.getKey() != null) {
				item.setId(keyHolder.getKey().longValue());
			}

		} else {
			String updateSql = "UPDATE item SET item_name = ?, price = ?, stock = ? WHERE id = ?";
			jdbcTemplate.update(updateSql, item.getItemName(), item.getPrice(), item.getStock(), item.getId());
		}
		return item;
	}

	@Override
	public List<Item> findAll() {
		return jdbcTemplate.query("SELECT * FROM item", new BeanPropertyRowMapper<>(Item.class));
	}

    @Override
    public Optional<Item> findById(Long id) {
        String sql = "SELECT * FROM item WHERE id = ?";
        List<Item> items = jdbcTemplate.query(sql, new Object[]{id}, new BeanPropertyRowMapper<>(Item.class));
        return items.isEmpty() ? Optional.empty() : Optional.of(items.get(0));
    }
}

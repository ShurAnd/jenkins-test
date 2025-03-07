package org.andrey.back2javawebapp.repositories;

import org.andrey.back2javawebapp.model.MagicCore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MagicCoreDataRepository implements DataRepository{

    private static final String tableName = "magic_core";
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public MagicCoreDataRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<MagicCore> getAll() {
        String sql = "SELECT * from " + tableName;
        List<MagicCore> result = jdbcTemplate.query(sql, (r, i) -> {
            MagicCore magicCore = new MagicCore();
            magicCore.setId(r.getLong("id"));
            magicCore.setQuantity(r.getBigDecimal("quantity"));
            magicCore.setOwner(r.getString("owner"));

            return magicCore;
        });
        return result;
    }

    @Override
    public MagicCore getOneById(Long id) {
        String sql = "SELECT * from " + tableName + " WHERE " + tableName + ".id = ?";
        List<MagicCore> result = jdbcTemplate.query(sql, (r, i) -> {
            MagicCore magicCore = new MagicCore();
            magicCore.setId(r.getLong("id"));
            magicCore.setQuantity(r.getBigDecimal("quantity"));
            magicCore.setOwner(r.getString("owner"));

            return magicCore;
        }, id);
        return result.stream().findFirst().orElse(null);
    }

    @Override
    public Long save(MagicCore magicCore) {
        String sql = "INSERT INTO " + tableName + " (quantity, owner) VALUES (?, ?)";
        jdbcTemplate.update(sql, magicCore.getQuantity(), magicCore.getOwner());

        return 0L;
    }

    @Override
    public Long update(MagicCore magicCore) {
        String sql = "UPDATE " + tableName + " SET quantity = ?, owner = ? WHERE " + tableName + ".id = ?";
        jdbcTemplate.update(sql, magicCore.getQuantity(), magicCore.getOwner(), magicCore.getId());
        return magicCore.getId();
    }

    @Override
    public Long deleteOneById(Long id) {
        String sql = "DELETE FROM " + tableName + " WHERE " + tableName + ".id = ?";
        jdbcTemplate.update(sql, id);
        return id;
    }
}

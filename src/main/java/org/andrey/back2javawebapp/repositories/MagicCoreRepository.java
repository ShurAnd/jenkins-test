package org.andrey.back2javawebapp.repositories;

import org.andrey.back2javawebapp.model.MagicCore;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface MagicCoreRepository extends CrudRepository<MagicCore, Long> {
    @Query("SELECT * FROM magic_core WHERE owner = :owner")
    List<MagicCore> findByOwner(String owner);
    @Modifying
    @Query("UPDATE magic_core SET quantity = :amount WHERE id = :id")
    void updateCoreAmount(Long id, BigDecimal amount);
}

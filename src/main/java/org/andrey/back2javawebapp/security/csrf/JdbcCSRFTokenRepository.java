package org.andrey.back2javawebapp.security.csrf;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JdbcCSRFTokenRepository extends CrudRepository<CustomCSRFToken, Integer> {
    Optional<CustomCSRFToken> findTokenByIdentifier(String identifier);
}

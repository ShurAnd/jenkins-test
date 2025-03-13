package org.andrey.back2javawebapp.repositories;

import org.andrey.back2javawebapp.users.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозитория для работы с данными пользователей
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}

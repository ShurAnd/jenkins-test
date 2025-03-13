package org.andrey.back2javawebapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Сервис для регистрационных операций с пользователями
 *
 */
@Service
public class RegistrationService {

    private final UserService userService;

    @Autowired
    public RegistrationService(UserService userService){
        this.userService = userService;
    }

    /**
     * Метод для регистрации нового пользователя
     */
    public void registerUser(String userString) throws Exception{
        userService.createUser(userString);
    }
}

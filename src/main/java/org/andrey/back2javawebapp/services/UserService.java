package org.andrey.back2javawebapp.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.andrey.back2javawebapp.repositories.UserRepository;
import org.andrey.back2javawebapp.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс сервис для работы с пользователями
 *
 */
@Service
public class UserService {

    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public UserService(UserRepository userRepository,
                       ObjectMapper objectMapper){
        this.userRepository = userRepository;
        this.objectMapper = objectMapper;
    }

    /**
     * Метод для поиска пользователя по Id
     * @param id id пользователя
     * @return Найденный пользователь
     */
    public User findUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User with id " + id + " тще ащгтв!"));
        user.setPassword("");

        return user;
    }

    /**
     * Метод для получения полного списка пользователей
     * @return список пользователей
     */
    public List<User> findAllUsers(){
        List<User> result = new ArrayList<>();
        userRepository.findAll().forEach(u -> {
            u.setPassword("");
            result.add(u);
        });

        return result;
    }

    /**
     * Метод для сохранения нового пользователя в базу
     * @param userString строковое представление пользователя
     * @return Новый созданный пользователь
     */
    public User createUser(String userString) throws Exception{
        User user = objectMapper.readValue(userString, User.class);
        user.setAuthorities(getDefaultAuthorities());
        user = userRepository.save(user);
        user.setPassword("");

        return user;
    }

    /**
     * Метод для обновления данных о пользователе
     * @param userString строковое представление пользователя
     * @return обновленная запись пользователя
     */
    public User updateUser(String userString) throws Exception{
        User user = objectMapper.readValue(userString, User.class);
        if (userRepository.findById(user.getId()).isEmpty()){
            throw new IllegalArgumentException("User with id " + user.getId() + " not found!");
        }

        user = userRepository.save(user);
        user.setPassword("");

        return user;
    }

    /**
     * Метод для удаления пользователя по id
     * @param id id пользователя которого надо удалить
     * @return true если пользователь удален, иначе false
     */
    public boolean deleteUserById(Long id){
        userRepository.deleteById(id);
        return true;
    }

    /**
     * Метод для получения списка дефолтный прав которые доступны пользователю при создании
     * @return список дефолтных доступных пользователю прав
     */
    private List<String> getDefaultAuthorities(){
        return List.of("READ");
    }
}

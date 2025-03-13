package org.andrey.back2javawebapp.users;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс для описания пользователя приложения
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    private Long id;
    private String username = "";
    private String password = "";
    private String firstName = "";
    private String lastName = "";
    private List<String> authorities = new ArrayList<>();
    private int age = 0;
}

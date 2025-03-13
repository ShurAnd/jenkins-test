package org.andrey.back2javawebapp.users;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Table;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс для описания пользователя приложения
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "users")
public class User {
    @Id
    private Long id;
    private String username = "";
    private String password = "";
    @Transient
    private String firstName = "";
    @Transient
    private String lastName = "";
    @Transient
    private List<String> authorities = new ArrayList<>();
    @Transient
    private int age = 0;
}

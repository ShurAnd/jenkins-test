package org.andrey.back2javawebapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

    private final JdbcTemplate jdbcTemplate;
//    private PasswordEncoder passwordEncoder;

    @Autowired
    public TestController(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Метод для преобразования имеющихся незахешированных паролей в хешированные
     * @return done по окончании рабоыт метода
     */
//    @GetMapping("/passwords")
    public String processExistingPasswords(){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);
        String query = "Select password from users";
        String updateQuery = "UPDATE users SET password = ? where password = ?";
        List<String> passwordList = jdbcTemplate.query(query,
                (r, i) -> r.getString("password"));
        List<String> processedPasswordList = passwordList.stream().map(passwordEncoder::encode).toList();
        for (int i = 0; i < passwordList.size(); i++){
            jdbcTemplate.update(updateQuery, processedPasswordList.get(i), passwordList.get(i));
        }

        return "done";
    }
}

package org.andrey.back2javawebapp.security.csrf;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "csrf")
public class CustomCSRFToken{
    @Id
    private int id;
    private String identifier;
    private String token;
}

package org.andrey.back2javawebapp.security;

import org.andrey.back2javawebapp.users.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;
import java.util.List;

public class CustomJdbcUserDetailsManager extends JdbcUserDetailsManager {

    public CustomJdbcUserDetailsManager(DataSource dataSource){
        super(dataSource);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SecurityUser securityUser = getSecurityUser(username);
        List<String> authorities = getAuthorities(username);
        securityUser.getUser().getAuthorities().addAll(authorities);

        return securityUser;
    }

    private SecurityUser getSecurityUser(String username) {
        String query = "select username, password, enabled from users where username = ?";
        List<User> userList = this.getJdbcTemplate().query(query, (r, i) -> {
            User u = new User();
            u.setUsername(r.getString("username"));
            u.setPassword(r.getString("password"));
            return u;
        }, username);
        if (userList.isEmpty()){
            throw new UsernameNotFoundException("No user with " + username + " username");
        }
        return new SecurityUser(userList.getFirst());
    }
    private List<String> getAuthorities(String username) {
        String query = "select authority from authorities where username = ?";
        return this.getJdbcTemplate().query(query,
                (r, i) ->  r.getString("authority"),
                username);
    }

}

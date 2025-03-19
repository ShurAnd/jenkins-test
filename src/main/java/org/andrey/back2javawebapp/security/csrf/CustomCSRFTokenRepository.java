package org.andrey.back2javawebapp.security.csrf;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.DefaultCsrfToken;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class CustomCSRFTokenRepository implements CsrfTokenRepository {

    private JdbcCSRFTokenRepository tokenRepository;

    @Autowired
    public CustomCSRFTokenRepository(JdbcCSRFTokenRepository tokenRepository){
        this.tokenRepository = tokenRepository;
    }

    @Override
    public CsrfToken generateToken(HttpServletRequest request) {
        String uuid = UUID.randomUUID().toString();
        return new DefaultCsrfToken("X-CSRF-TOKEN", "_csrf", uuid);
    }

    @Override
    public void saveToken(CsrfToken token, HttpServletRequest request, HttpServletResponse response) {
        String identifier = request.getHeader("X-IDENTIFIER");
        Optional<CustomCSRFToken> existingToken = tokenRepository.findTokenByIdentifier(identifier);
        CustomCSRFToken csrfToken;
        if (existingToken.isPresent()){
            csrfToken = existingToken.get();
            csrfToken.setToken(token.getToken());
        }else{
            csrfToken = new CustomCSRFToken();
            csrfToken.setIdentifier(identifier);
            csrfToken.setToken(token.getToken());
        }
        tokenRepository.save(csrfToken);
    }

    @Override
    public CsrfToken loadToken(HttpServletRequest request) {
        String identifier = request.getHeader("X-IDENTIFIER");
        Optional<CustomCSRFToken> csrfToken = tokenRepository.findTokenByIdentifier(identifier);
        if (csrfToken.isPresent()){
            CustomCSRFToken result = csrfToken.get();
            return new DefaultCsrfToken("X-CSRF-TOKEN", "_csrf", result.getToken());
        }

        return null;
    }
}

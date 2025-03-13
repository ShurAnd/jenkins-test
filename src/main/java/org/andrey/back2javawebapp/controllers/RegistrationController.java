package org.andrey.back2javawebapp.controllers;

import org.andrey.back2javawebapp.services.RegistrationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Класс контроллер для регситрации новых пользователей
 */
@RestController
@RequestMapping("/register")
public class RegistrationController {

    private static final Logger logger = LoggerFactory.getLogger(RegistrationController.class);
    private static final String TAG = "REGISTRATION CONTROLLER";

    private final RegistrationService registrationService;

    @Autowired
    public RegistrationController(RegistrationService registrationService){
        this.registrationService = registrationService;
    }

    @PostMapping
    public ResponseEntity<?> registerNewUser(@RequestBody String userString) throws Exception{
        logger.info("{} - Registration request", TAG);
        registrationService.registerUser(userString);
        logger.info("{} - Registration request processed", TAG);

        return ResponseEntity.created(null).build();
    }
}

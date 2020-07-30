package com.example.twilio_client_spring.controller.api;

import com.example.twilio_client_spring.model.User;
import com.example.twilio_client_spring.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    public Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "token", method = RequestMethod.GET)
    public ResponseEntity<User> getUserTokens(){
        User user = userService.createToken();
        return ResponseEntity.ok(user);
    }

}

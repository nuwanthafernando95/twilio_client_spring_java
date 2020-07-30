package com.example.twilio_client_spring.service;

import com.example.twilio_client_spring.constants.AppStatics;
import com.example.twilio_client_spring.model.User;
import com.github.javafaker.Faker;
import com.twilio.jwt.accesstoken.AccessToken;
import com.twilio.jwt.accesstoken.VoiceGrant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class UserService {
    public Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Value("${twilio.account_sid}")
    private String acctSid;

    @Value("${twilio.twiml.app.sid}")
    private String applicationSid;

    @Value("${twilio.api.key}")
    private String apiKey;

    @Value("${twilio.api.secret}")
    private String apiSecret;

    public User createToken(){
        LOGGER.info("Enter createToken() in UserService");
        String identify = generateIdentity();
        return createJsonAccessToken(identify);
    }


    private String generateIdentity() {
        LOGGER.info("Enter generateIdentity() in UserService");
        // Create a Faker instance to generate a random username for the connecting user
        Faker faker = new Faker();
        String current_user_name = faker.name().firstName() + faker.address().zipCode();
        AppStatics.current_logged_user = current_user_name;
        return current_user_name;
    }

    private User createJsonAccessToken(String identity) {
        LOGGER.info("Enter createJsonAccessToken() in UserService [identify -{}]", identity);
        User user = new User();
        // Create Voice grant
        VoiceGrant grant = new VoiceGrant();
        grant.setOutgoingApplicationSid(applicationSid);

        // Optional: add to allow incoming calls
        grant.setIncomingAllow(true);

        // Create access token
        AccessToken accessToken = new AccessToken.Builder(acctSid, apiKey, apiSecret)
                .identity(identity)
                .grant(grant)
                .build();

        String token = accessToken.toJwt();

        //Set user values
        user.setIdentity(identity);
        user.setToken(token);

        return user;
    }
}

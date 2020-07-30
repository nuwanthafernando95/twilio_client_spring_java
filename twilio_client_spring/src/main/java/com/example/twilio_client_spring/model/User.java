package com.example.twilio_client_spring.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class User {
    private String identity;
    private String token;
}

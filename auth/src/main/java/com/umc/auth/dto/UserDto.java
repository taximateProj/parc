package com.example.oauthsession.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private String username;
    private String name;
    private String role;
    private String email;
    private String university;
    private String accountNumber;
}

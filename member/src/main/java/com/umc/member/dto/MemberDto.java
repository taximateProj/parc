package com.umc.member.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDto {
    private String username;
    private String name;
    private String role;
    private String email;
    private String university;
    private String accountNumber;
}

package com.umc.member.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

public class MemberRequest {
    @Getter
    public static class JoinDTO {
        @NotBlank
        String name;
        @NotNull
        Integer gender;
        @NotNull
        String school;
        @NotNull
        String accountNumber;
        @Size(min = 5, max = 50)
        String address;


    }
}

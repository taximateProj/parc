package com.umc.member.converter;

import com.umc.member.domain.Member;
import com.umc.member.domain.enums.Gender;
import com.umc.member.dto.MemberRequest;
import com.umc.member.dto.MemberResponse;

import java.time.LocalDateTime;

import static com.umc.member.dto.MemberResponse.*;

public class MemberConverter {
    public static JoinResultDTO toJoinResultDTO(Member member) {
        return JoinResultDTO.builder()
                .memberID(member.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }
    public static Member toMember(MemberRequest.JoinDTO request) {

        Gender gender = null;

        switch (request.getGender()) {
            case 1:
                gender = Gender.MALE;
                break;
            case 2:
                gender = Gender.FEMALE;
                break;
            case 3:
                gender = Gender.NONE;
        }
        return Member.builder()
                .gender(gender)
                .name(request.getName())
                
                .build();

    }
}

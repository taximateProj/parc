package com.umc.member.controller;


import com.umc.member.apiPayload.ApiResponse;
import com.umc.member.converter.MemberConverter;
import com.umc.member.domain.Member;
import com.umc.member.dto.MemberRequest;
import com.umc.member.dto.MemberResponse;
import com.umc.member.service.MemberCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/login")
public class LoginController {
    MemberCommandService memberCommandService;

    @PostMapping("/detail")
    public ApiResponse<MemberResponse.JoinResultDTO> join(@RequestBody MemberRequest.JoinDTO request) {
        Member member = memberCommandService.joinMember(request);
        return ApiResponse.onSuccess(MemberConverter.toJoinResultDTO(member));
    }
}

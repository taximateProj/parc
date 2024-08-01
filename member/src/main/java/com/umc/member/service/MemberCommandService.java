package com.umc.member.service;


import com.umc.member.domain.Member;
import com.umc.member.dto.MemberRequest;

public interface MemberCommandService {
    Member joinMember(MemberRequest.JoinDTO request);
}

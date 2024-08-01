package com.umc.member.service;



import com.umc.member.domain.Member;
import com.umc.member.dto.MemberRequest;
import com.umc.member.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@org.springframework.transaction.annotation.Transactional(readOnly = true)
public class MemberCommandServiceImpl implements MemberCommandService{
    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public Member joinMember(MemberRequest.JoinDTO request) {
        Member newMember = null;


        return memberRepository.save(newMember);
    }
}

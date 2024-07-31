package com.umc.auth.service;


import com.umc.auth.dto.KakaoMemberDetails;
import com.umc.auth.dto.KakaoUserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class KakaoMemberDetailsService extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;

    @Transactional
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        KakaoUserInfo kakaoUserInfo = new KakaoUserInfo(oAuth2User.getAttributes()); //email 데이터의 구조 때문에 따로 뺌

        System.out.println(kakaoUserInfo.getEmail());
        Member member = memberRepository.findByEmail(kakaoUserInfo.getEmail())
                .orElseGet(() ->
                        memberRepository.save(
                                Member.builder()
                                        .email(kakaoUserInfo.getEmail())
                                        .name(oAuth2User.getName())
                                        .role(Role.USER)
                                        .gender(Gender.NONE)
                                        .build()
                        )
                );
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(member.getRole().name());

        return new KakaoMemberDetails(String.valueOf(member.getEmail()),
                Collections.singletonList(authority),
                oAuth2User.getAttributes());
    }
}

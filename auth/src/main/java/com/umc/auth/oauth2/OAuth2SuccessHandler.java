package com.umc.auth.oauth2;


import com.umc.auth.dto.CustomOAuth2User;
import com.umc.auth.dto.TokenDto;
import com.umc.auth.entity.RefreshToken;
import com.umc.auth.jwt.TokenProvider;
import com.umc.auth.repository.RefreshTokenRedisRepository;
import com.umc.member.apiPayload.exception.MemberNotFoundException;
import com.umc.member.repository.MemberRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private static final String REDIRECT_URI = "http://localhost:8080/api/sign/login/kakao?accessToken=%s&refreshToken=%s";
    private final TokenProvider tokenProvider;
    private final MemberRepository memberRepository;
    private final RefreshTokenRedisRepository refreshTokenRepository;

    //로그인 성공 후 처리할 로직 - JWT 토큰 생성 후 응답 헤더에 추가
    @Transactional
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        CustomOAuth2User customOAuth2User = (CustomOAuth2User) authentication.getPrincipal();
        System.out.println(customOAuth2User.getName());

        String username = customOAuth2User.getName();


        Member member = memberRepository.findByUsername(username);// username이 키
        if (member == null) {
            throw new MemberNotFoundException();
        }

        TokenDto tokenDto = tokenProvider.createToken(member.getUsername(), member.getRole().name());


        saveRefreshTokenOnRedis(member, tokenDto);


        String redirectURI = String.format(REDIRECT_URI, tokenDto.getAccessToken(), tokenDto.getRefreshToken());
        System.out.println(tokenDto.getAccessToken());

        response.addHeader("Authorization", tokenDto.getAccessToken());
        response.addCookie(createCookie("Authorization", tokenDto.getRefreshToken())); // token 어떤 식으로 넘겨줘야 되는지?
        response.sendRedirect("http://localhost:3000/");

//        getRedirectStrategy().sendRedirect(request, response, redirectURI);
    }

    private void saveRefreshTokenOnRedis(Member member, TokenDto tokenDto) {
        List<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
        simpleGrantedAuthorities.add(new SimpleGrantedAuthority(member.getRole().name()));
        refreshTokenRepository.save(RefreshToken.builder()
                .id(member.getEmail())
                .authorities(simpleGrantedAuthorities)
                .refreshToken(tokenDto.getRefreshToken())
                .build());
    }

    private Cookie createCookie(String key, String value) {

        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(60 * 60 * 60);
        cookie.setPath("/");
        cookie.setHttpOnly(true);

        return cookie;
    }
}

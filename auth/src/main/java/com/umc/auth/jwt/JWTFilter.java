package com.example.oauthsession.jwt;

import com.example.oauthsession.dto.CustomOAuth2UserDto;
import com.example.oauthsession.dto.UserDto;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.net.Authenticator;

public class JWTFilter extends OncePerRequestFilter { // 한번만 검증

    private final JWTUtil jwtUtil; // 쿠키에서 jwt 꺼내서 검증해야하기 때문에 필요

    public JWTFilter(JWTUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestUri = request.getRequestURI();

//        if (requestUri.matches("^\\/login(?:\\/.*)?$")) {
//
//            filterChain.doFilter(request, response);
//            return;
//        }
//        if (requestUri.matches("^\\/oauth2(?:\\/.*)?$")) {
//
//            filterChain.doFilter(request, response);
//            return;
//        }

        String authorization = null;
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                System.out.println(cookie.getName());
                System.out.println(cookie.getValue());

                if (cookie.getName().equals("Authorization")) {
                    authorization = cookie.getValue();
                }
            }
        }


        if (authorization == null) {
            System.out.println("token null");
            filterChain.doFilter(request, response); //  다음 필터로 넘겨주기

            return;
        }

        String token = authorization;

        // 토큰 검증
        if (jwtUtil.isExpired(token)) {
            System.out.println("token expired");
            filterChain.doFilter(request, response);

            return;
        }

        String username = jwtUtil.getUsername(token);
        String role = jwtUtil.getRole(token);

        UserDto userDto = new UserDto();
        userDto.setUsername(username);
        userDto.setRole(role);

        CustomOAuth2UserDto customOAuth2UserDto = new CustomOAuth2UserDto(userDto);

        Authentication authToken = new UsernamePasswordAuthenticationToken(customOAuth2UserDto, null, customOAuth2UserDto.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authToken);

        filterChain.doFilter(request, response);

    }
}

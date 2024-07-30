package com.example.oauthsession.service;

import com.example.oauthsession.dto.*;
import com.example.oauthsession.entity.User;
import com.example.oauthsession.repository.UserRepository;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.stereotype.Service;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService{

    private  final UserRepository userRepository;

    public CustomOAuth2UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);
        System.out.println(oAuth2User.getAttributes());


        String registrationId = userRequest.getClientRegistration().getRegistrationId(); // 네이버인지 구글인지 구분하는 변수
        OAuth2Response oAuth2Response = null;

        if (registrationId.equals("naver")) { // 네이버와 구글이 보내주는 인증 정보 규격이 다르기 때문에
            oAuth2Response = new NaverResponseDto(oAuth2User.getAttributes());
        } else if (registrationId.equals("google")) {
            oAuth2Response = new GoogleResponseDto(oAuth2User.getAttributes());
        } else if (registrationId.equals("kakao")) {
            oAuth2Response = new KakaoResponseDto(oAuth2User.getAttributes());
        }
        else {
            return null;
        }

        // 나머지 구현
        String username = oAuth2Response.getProvider()+" "+oAuth2Response.getProviderId();
        System.out.println(username);
        User existData = userRepository.findByUsername(username); // 존재하는지 조회
        String role = "ROLE_USER";

        if (existData == null) {

            User userEntity = new User();
            userEntity.setUsername(username);
            userEntity.setEmail(oAuth2Response.getEmail());
            userEntity.setRole(role);

            userRepository.save(userEntity);

            UserDto userDto = new UserDto();
            userDto.setUsername(username);
            userDto.setName(oAuth2Response.getName());
            userDto.setRole("ROLE_USER");

            return new CustomOAuth2UserDto(userDto);



        } else { // 왜 있을 때는 다시 set 하지?? 그냥 이미 있는 유저라고 반환해야되는거 아닌가? -> login 하면서 회원 정보 update 해주는 정책 (이거는 정해야할듯?)

            existData.setUsername(username);
            existData.setEmail(oAuth2Response.getEmail());
            role = existData.getRole();
            userRepository.save(existData);

            UserDto userDto = new UserDto();
            userDto.setUsername(username);
            userDto.setName(oAuth2Response.getName());
            userDto.setRole("ROLE_USER");

            return new CustomOAuth2UserDto(userDto);
        }


    }
}

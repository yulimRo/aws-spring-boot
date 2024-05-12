package com.project.awsspringboot.config.auth;

import com.project.awsspringboot.config.auth.dto.OAuthAttributes;
import com.project.awsspringboot.config.auth.dto.SessionUser;
import com.project.awsspringboot.domain.user.User;
import com.project.awsspringboot.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.build.Plugin;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final UserRepository userRepository;
    private final HttpSession httpSession;


    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();

        OAuth2User oAuth2User = delegate.loadUser(userRequest);


        //현재로그인 진행 중인 서비스를 구분하는 코드
        //구글만 사용하는 불필요한 값이지만, 네이버/인지 구분하기 위한 용도
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        //OAuth2로그인 진행 시 키가 되는 필드값(Primary key)
        //구글의 경우 기본적으로 코드를 지원. 네이버, 카카오는 기본 지원하지 않는다 구글은 기본 코드가 "sub" 이다.
        //이후 네이버 로그인과 구글 로그인을 동시 지원할 때 사용
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();

        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName,oAuth2User.getAttributes());

        User user = saveOrUpdate(attributes);

        httpSession.setAttribute("user",new SessionUser(user));

        return new DefaultOAuth2User(Collections.singleton(
                new SimpleGrantedAuthority(user.getRoleKey())),
                attributes.getAttributes(),
                attributes.getNameAttributeKey()
        );
    }

    private User saveOrUpdate(OAuthAttributes attributes) {
        User user = userRepository.findByEmail(attributes.getEmail()).map(entity -> entity.update(attributes.getName(), attributes.getPicture())).orElse(attributes.toEntity());

        return userRepository.save(user);

    }
}

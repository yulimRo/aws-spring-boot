package com.project.awsspringboot.config.auth.dto;

import com.project.awsspringboot.domain.user.Role;
import com.project.awsspringboot.domain.user.User;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;


/*
    OAuthAttributes
    -OAuth2UserService를 통해 가져온 OAuth2User의 attribute를 담을 클래스 =
    - 네이버등 다른 소셜 로그인도 이 클래스를 사용

    of()
    - OAuth2User에서 반환하는 사용자 정보는 Map -> 하나하나를 변환해야한다.

    toEntity()
    - User 엔티티 생성
    - OAuthAttributes에서 엔티티 생성하는 시점은 처음 가입할 때
    - 가입할 때의 기본 권한을 GUEST로 지정
    -OAuthAttribute 클래스 생성이 끝났으면 같은 패키지에 SessionUser클래스 생성

 */
@Getter
public class OAuthAttributes {
    private Map<String,Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String email;
    private String picture;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String name, String email, String picture) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;
        this.picture = picture;
    }

    public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String,Object> attributes){
        return ofGoogle(userNameAttributeName, attributes);
    }

    private static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String,Object> attributes){
        return OAuthAttributes.builder()
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .picture((String) attributes.get("picture"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    public User toEntity(){
        return User.builder()
                .name(name)
                .email(email)
                .picture(picture)
                .role(Role.GUEST)
                .build();
    }
}

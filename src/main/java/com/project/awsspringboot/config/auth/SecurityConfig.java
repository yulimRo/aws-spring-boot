package com.project.awsspringboot.config.auth;

import com.project.awsspringboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity //Spring Security 설정등을 활성화시킴
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    protected void configure(HttpSecurity http) throws Exception {

        http
                //h2-console을 사용하기 위해 해당 옵션들을 disable합ㄴ다.
                .csrf().disable().headers().frameOptions().disable()
                .and()
                //URL별 권한 관리를 설정하는 옵션의 시작점
                .authorizeRequests()
                //권한관리대상을지정하는옵션
                //URL, HTTP 메소드별로 관리가 가능
                //"/" 등을 지정된 URL들은 permitALl() 옵션을 통해 전체 열람 권한 주어짐
                //"/api/v1/**" 주소를 가진 API는 USER 권한을 가진 사람만 가능하도록 했다.
                .antMatchers("/","/css/**","images/**","/js/**","/h2-console/**").permitAll()
                .antMatchers("/api/v1/**").hasRole(Role.USER.name())

                //설정된 값들 이외 나머지 URL
                .anyRequest()
                //나머지 URL들은 모두 인증된 사용자들에게만 허용하게 한다.( 로그인한 사용자)
                .authenticated()
                .and()
                //로그아웃 기능에 대한 여러 설정의 진입점
                .logout()
                .logoutSuccessUrl("/")
                .and()
                //oauth2 로그인 기능에 대한 설정의 진입
                .oauth2Login()
                //oauth2 로그인 성공이후 사용자 정보 가져올 때의 설정
                .userInfoEndpoint()

                //소셜 로그인 성공 시 후속조치를 진행할 UserService 인터페잇의 구현체를 등록
                //리소스 서버(즉, 소셜서비스) 에서 사용자 정보를 가져온 상태에서 추가로 진행하고자하는 기능을 명시
                .userService(customOAuth2UserService);
    }
}

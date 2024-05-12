package com.project.awsspringboot.domain.user;

import com.project.awsspringboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column
    private String picture;

    /*
        @Enumerated(EnumType.STRING)
        JPA로 데이터베이스로 저장할 때 Enum 값을 어떤 형태로 저장항지를 결정
        기본적으로 int로 된 숫자가 저장된다.
        숫자로 저장되면 데이터베이스로 확인할 때 그 값이 무슨 코드를 의미하는지 알 수 없다.
        그래서 문자열 EnumType.STRING로 저장될 수 있도록 선언
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Builder
    public User(String name, String email, String picture, Role role) {
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.role = role;
    }

    /*
        구글 사용자 정보가 업데이트 되었을 때를 대비하여 update 기능도 같이 구현.
     */
    public User update(String name, String picture){
        this.name = name;
        this.picture = picture;

        return this;
    }

    public String getRoleKey(){
        return this.role.getKey();
    }
}

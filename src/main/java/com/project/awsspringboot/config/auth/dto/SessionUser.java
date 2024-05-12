package com.project.awsspringboot.config.auth.dto;

import com.project.awsspringboot.domain.user.User;
import lombok.Getter;

import java.io.Serializable;

/*
    인증된 사용자 정보만 필요.

    왜 User 클래스를 사용하지 않고 SessionUser를 별도로 만들었나?
    - User를 사용하면 에러가 발생한다. -> User 클래스는 직렬화가 되어있지 않기 때문에
    - User 클래스를 직렬화 코드를 넣지 않은 이유 -> User 클래스는 엔티티이기 때문
    - 직렬화 대상에 자식들 까지 포함되어 성능이슈, 부수효과가 발생
    - 그러므로 직렬화 기능을 가진 세션 Dto를 하나 추가로 만든 것.
 */
@Getter
public class SessionUser implements Serializable {
    private String name;
    private String email;
    private String picture;

    public SessionUser(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}

package com.project.awsspringboot.domain.posts;

import com.project.awsspringboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/*
    @Entity
    - 테이블과 링크될 클래스임
    - 기본값으로 클래스의 카멜케이스이름을 언더스코어네이밍으로 테이블이름을 매칭

    @Id
    - 해당 테이블의 PK필드

    @GeneratedValue
    - PK 생성 규칙
    - 스프링 2.0에서는 GenerationType.IDENTITY옵션을 추가해야만 auto)increment가 된다.

    @Column
    - 테이블 칼럼을 나타낸다. (필수 선언 X)
    - 사용하는 이유는 기본값외에 추가로 변경이 필요한 옵션이 있으면 사용
    - 문자열의 경우 VARCHAR(255)가 기본값, 사이즈를 500으로 늘리고 싶거나 타입을 TEXT로 변경할 때 사용

    [추천]
    Entity의 PK는 Long타입의 auto_increment를 추천

    @NoArgConstructor
    - 기본 생성자 자동 추가
    - public Posts(){} 와 같은 효과

    @Getter
    - 클래스 내 모든 필드의 Getter 메서드를 자동생성

    @Builder
    - 해당 클래스의 빌더 패턴 클래스를 생성
    - 생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함


 */
@NoArgsConstructor
@Entity
@Getter
public class Posts extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;
    private String author;

    @Builder
    public Posts(Long id, String title, String content, String author) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update (String title, String content){
        this.title = title;
        this.content = content;
    }
}

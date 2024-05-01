package com.project.awsspringboot.web.dto;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/*
    assertThat
     - assertj라는 테스트 검증 라이브러리 검증 메서드
     - 검증하고 싶은 대상을 메서드 인자로 받음
     - 메서드 체이닝이 지원되어 isEqualTo와 같이 메서드를 이어서 사용할 수 있음
     ! Junit 의 assertThat을 쓸 경우 CoreMathchers 라이브러리가 부가적으로 필요하여 assertj를 사용.
     isEqualTo
     - assertj의 동등비교 메서드
     - assertThat에 있는 값과 isEqualTo의 값을 비교해서 같을 때만 성공
 */
public class HelloResponseDtoTest {

    @Test
    public void 롬복_기능_테스트(){
        String name = "test";
        int amount = 10;

        HelloResponseDto dto = new HelloResponseDto(name, amount);

        assertThat(dto.getName()).isEqualTo("test");
        assertThat(dto.getAmount()).isEqualTo(10);
    }
}

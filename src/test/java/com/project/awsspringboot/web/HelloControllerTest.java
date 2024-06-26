package com.project.awsspringboot.web;

import javafx.scene.shape.PathElementBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/*
    @RunWith(SpringRunner.class)
    - 테스트를 진행할 때 Junit에 내장된 실행자 외에 다른 실행자를 실행 시킨다.
    - 여기서는 SpringRunner라는 스프링 실행자를 사용
    - 즉, 스프링부트 테스트와 Junit 사이에 연결자 역할을 한다.

    @WebMvcTest
    - 여러 스프링 테스트 어노테이션 중 'Web'에 집중할 수 있는 어노테이션
    - 선언할 경우, @Controller, @ControllerAdvice등을 사용할 수 있다.
    - @Service, @Component, @Repository등은 사용할 수 없다.
    - 여기선 컨트롤러만 사용하기 때문에 선언한다.

    MockMvc
    - 웹 API 테스트할 때 사용
    - 스프링 MVC테스트의 시적점
 */
@RunWith(SpringRunner.class)
@WebMvcTest(controllers = HelloController.class)
public class HelloControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void hello가_리턴된다() throws Exception{
        String hello = "hello";



        /*
            mvc.perform(get("/hello"))
                - MockMvc를 통해 /hello 주소로 HTTP GET 요청을 합니다.
                - 체이닝이 지원되어 아래와 같이 여러검증 기능을 이어서 선언할 수 있습니다.
            .andExpect(status().isOk())
                - mvc.perform()의 결과 검증
                - HTTP Header의 Status를 검증
                - 우리가 흔히 알고 있는 200, 404, 500 등의 상태를 검증합니다.
                여기선 OK즉, 200인지 아닌지를 검
             .andExpect(content().string(hello))
                - mvc.peform의 결과를 검증
                - 응답 본문의 내용을 검증
                - Controller에서 "hello"를 리턴하기 때문에 이 값이 맞는지 검증
         */
//        mvc.perform(get("/hello"))
//                .andExpect(status().isOk())
//                .andExpect(content().string(hello));

        String name = "홍길동";
        int amount = 10000;

        /*

            param
            - API 테스트할 때 사용될 요청 파라미터를 설정
            - 단, 값은 String만 허용
            - 그래서 숫자/날짜등의 데이터도 등록할대 문자열로 변경해야만 가능


            jsonPath
            - JSON 응답값을 필드별로 검증할 수 있는 메서드
            - $를 기준으로 필드명을 명시합니다.
            여기서는 name과 amount를 검증하니 $.name, $.amount로 검증한다.
         */

        mvc.perform(get("/hello")
                        .param("name",name)
                        .param("amount",String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount",is(amount)));
    }
}

package com.project.awsspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
  @SpringBootApplication
  - Spring boot의 자동 설정
  - Spring Bean 읽기&생성을 자동으로 설정
  - 해당 어노테이션 위치부터 읽어가기때문에 이 클래스는 항상 최상단에 위치해야한다.

  SpringApplication.run()
  - 내장 was가 구동된다.

 */
@SpringBootApplication
public class Application {
    public static void main (String[] args){
        SpringApplication.run(Application.class, args);
    }
}

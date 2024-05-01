package com.project.awsspringboot.web;

import com.project.awsspringboot.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/*
 @RestController
 - 컨트롤러를 JSON을 반환하는 컨트롤러로 생성
 -  @ResponseBody를 각 메소드마다 명시하지 않아도 된다.
 */
@RestController
public class HelloController {

    @GetMapping("/hello")
    public HelloResponseDto hello(@RequestParam("name") String name, @RequestParam("amount") int amount) {
     return new HelloResponseDto(name, amount);
    }
}

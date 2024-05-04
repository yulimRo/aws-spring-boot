package com.project.awsspringboot.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    /*
        mustache starter 로 인해서 컨트롤러에서 문자열을 반환할 때
        앞의 경로와 뒤의 파일 확장자는 자동으로 지정되어 전환된것을 View Resolver가 처리하게 됩니다.
        prefix : src/main/resources/templates
        suffix : .mustache
     */
    @GetMapping("/")
    public String index(){
        return "index";

    }
}

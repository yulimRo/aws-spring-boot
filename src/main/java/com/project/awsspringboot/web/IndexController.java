package com.project.awsspringboot.web;

import com.project.awsspringboot.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;

    /*
        mustache starter 로 인해서 컨트롤러에서 문자열을 반환할 때
        앞의 경로와 뒤의 파일 확장자는 자동으로 지정되어 전환된것을 View Resolver가 처리하게 됩니다.
        prefix : src/main/resources/templates
        suffix : .mustache
     */
    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("posts",postsService.findAllDesc());
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(Model model, @PathVariable("id") Long id){

        model.addAttribute("post",postsService.findById(id));
        return "posts-update";
    }


}

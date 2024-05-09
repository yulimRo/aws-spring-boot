package com.project.awsspringboot.service;


import com.project.awsspringboot.domain.posts.Posts;
import com.project.awsspringboot.domain.posts.PostsRepository;
import com.project.awsspringboot.web.dto.PostsListResponseDto;
import com.project.awsspringboot.web.dto.PostsResponseDto;
import com.project.awsspringboot.web.dto.PostsSaveRequestDto;
import com.project.awsspringboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    public Long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto){
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    @Transactional
    public Long delete(Long id){
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));

        postsRepository.delete(posts);

        return id;
    }

    public PostsResponseDto findById(Long id){
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));

        return new PostsResponseDto(entity);

    }

    /*
        readOnly 옵션
        트랜잭션 범위는 유지하되, 조회 기능만 남겨두어 조회속도가 개선된다.
     */
    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc() {
        List<PostsListResponseDto> postsList = postsRepository.findAllDesc().stream().map(PostsListResponseDto::new)
                .collect(Collectors.toList());

        //        List<PostsListResponseDto> postsList = postsRepository.findAllDesc().stream().map(posts -> new PostsListResponseDto(posts))
        //                .collect(Collectors.toList());

        return postsList;
    }



}

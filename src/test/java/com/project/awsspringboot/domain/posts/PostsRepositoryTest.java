package com.project.awsspringboot.domain.posts;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/*
    @After
    - Junit에서 단위테스트가 끝날 때마다 수행된는 메서드를 지정
    - !여러 테스트가 동시에 수행되면! 테스트용 데이터베이스인 H2에 데이터가 그대로 남아있어 다음 테스트 실행 시 실패할 수 있다.

    @postsRepository.save
    - 테이블 posts에 insert/update 쿼리를 실행
    - id 값이 있다면 update, id가 없다면, insert 쿼리가 실행

    @postsRepository.findAlln
    - 테이블 posts에 있는 모든 데이터를 조회해오는 메서드

    [참고]
    - 별다른 설정없이 SpringBootTest를 실행하면 H2 데이터베이스를 자동으로 실행

 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @After
    public void cleanup(){
        //postsRepository.deleteAll();
    }
//
//    @Test
//    public void 게시글저장_불러오기(){
//        String title = "타이틀";
//        String content = "내용";
//
//        postsRepository.save(Posts.builder()
//                .title(title)
//                .content(content)
//                .author("저자").build());
//
//        List<Posts> postsList = postsRepository.findAll();
//
//        Posts post = postsList.get(0);
//
//        assertThat(post.getTitle()).isEqualTo(title);
//        assertThat(post.getContent()).isEqualTo(content);
//        assertThat(post.getAuthor()).isEqualTo("저자");
//
//    }

    @Test
    public void BaseTimeEntity_등록() {
        LocalDateTime now = LocalDateTime.of(2024,5,1,0,0,0);

        postsRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("author")
                .build());

        List<Posts> postsList = postsRepository.findAll();

        Posts posts = postsList.get(0);

        System.out.println(">>>>>>> createDate=" + posts.getCreatedDate() + ", modifiedDate="+ posts.getModifiedDate());

        assertThat(posts.getCreatedDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);
    }

}

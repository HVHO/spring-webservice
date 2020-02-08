package com.hvho1119.springboot.web.dto;

import com.hvho1119.springboot.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsUpdateRequestDto {
    private String title;
    private String content;

    @Builder
    public PostsUpdateRequestDto(String title, String content, String author) {
        this.title = title;
        this.content = content;
    }

    public Posts toEntity() {
        return Posts.builder()
                .title(title)
                .content(content)
                .build();
    }

}

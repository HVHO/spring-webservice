package com.hvho1119.springboot.service.posts;

import com.hvho1119.springboot.domain.posts.Posts;
import com.hvho1119.springboot.domain.posts.PostsRepository;
import com.hvho1119.springboot.web.dto.PostsResponseDto;
import com.hvho1119.springboot.web.dto.PostsSaveRequestDto;
import com.hvho1119.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    public Long update(Long id, PostsUpdateRequestDto requestDto) {

        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Cann't find such post. id="+id));

        posts.update(requestDto.getTitle(),requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById(Long id) {

        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Cann't find such post. id="+id));

        return new PostsResponseDto(entity);
    }
}

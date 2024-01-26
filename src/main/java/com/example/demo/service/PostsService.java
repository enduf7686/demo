package com.example.demo.service;

import com.example.demo.controller.dto.PostsResponseDto;
import com.example.demo.controller.dto.PostsSaveRequestDto;
import com.example.demo.controller.dto.PostsUpdateRequestDto;
import com.example.demo.domain.Posts;
import com.example.demo.domain.PostsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        Posts save = postsRepository.save(requestDto.toEntity());
        return save.getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);

        return new PostsResponseDto(entity);
    }
}

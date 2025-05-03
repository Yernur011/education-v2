package com.rdlab.education.service.crud.impl;

import com.rdlab.education.domain.dto.forum.TagsDto;
import com.rdlab.education.domain.enums.ForumQuestionState;
import com.rdlab.education.domain.repository.edu.ForumQuestionRepository;
import com.rdlab.education.domain.repository.edu.TagsRepository;
import com.rdlab.education.service.crud.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {
    private final TagsRepository tagsRepository;
    private final ForumQuestionRepository forumQuestionRepository;

    @Override
    public List<TagsDto> findAll() {
        return tagsRepository.findAll().stream()
                .map(tags -> TagsDto.builder()
                        .id(tags.getId())
                        .tagName(tags.getName())
                        .tagCount(forumQuestionRepository.countQuestionsByTagId(tags.getId(), ForumQuestionState.APPROVED.getState()))
                        .build()
                ).toList();
    }

}

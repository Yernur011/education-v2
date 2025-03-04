package com.rdlab.education.service.crud.impl;

import com.rdlab.education.domain.dto.forum.ForumCategoryDto;
import com.rdlab.education.domain.entity.edu.ForumCategory;
import com.rdlab.education.domain.enums.ForumQuestionState;
import com.rdlab.education.domain.repository.edu.ForumCategoryRepository;
import com.rdlab.education.domain.repository.edu.ForumQuestionRepository;
import com.rdlab.education.service.crud.ForumCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ForumCategoryServiceImpl implements ForumCategoryService {
    private final ForumCategoryRepository forumCategoryRepository;
    private final ForumQuestionRepository forumQuestionRepository;

    @Override
    public List<ForumCategoryDto> findAll() {
        return forumCategoryRepository.findAll().stream()
                .map(category -> ForumCategoryDto.builder()
                        .id(category.getId())
                        .categoryName(category.getCategoryName())
                        .forumsCount(forumQuestionRepository
                                .countQuestionsByCategoryId(category.getId(), ForumQuestionState.APPROVED.getState()))
                        .build()
                ).toList();
    }

}

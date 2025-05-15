package com.legalDigital.education.service.crud.impl;

import com.legalDigital.education.domain.dto.forum.ForumCategoryDto;
import com.legalDigital.education.domain.enums.ForumQuestionState;
import com.legalDigital.education.domain.repository.edu.ForumCategoryRepository;
import com.legalDigital.education.domain.repository.edu.ForumQuestionRepository;
import com.legalDigital.education.service.crud.ForumCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

package com.rdlab.education.service.crud;

import com.rdlab.education.domain.dto.article.ArticleDto;
import com.rdlab.education.domain.dto.page.PageableDto;

import java.util.List;
import java.util.Optional;

public interface ArticleService {
    ArticleDto create(ArticleDto dto);
    Optional<ArticleDto> findById(Long id);
    List<ArticleDto> findAll();
    PageableDto<ArticleDto> findAll(int page, int size);
    ArticleDto update(Long id, ArticleDto dto);
    void delete(Long id);
}
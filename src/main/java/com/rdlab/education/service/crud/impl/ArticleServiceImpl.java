package com.rdlab.education.service.crud.impl;

import com.rdlab.education.domain.dto.article.ArticleDto;
import com.rdlab.education.domain.entity.edu.Articles;
import com.rdlab.education.domain.entity.image.Base64Images;
import com.rdlab.education.domain.mapper.ArticleMapper;
import com.rdlab.education.domain.repository.edu.ArticlesRepository;
import com.rdlab.education.service.crud.ArticleService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private final ArticlesRepository repository;

    @Qualifier("articleMapper")
    private final ArticleMapper mapper;

    @Override
    public ArticleDto create(ArticleDto dto) {
        Articles entity = mapper.toEntity(dto);
        entity.setFile(new Base64Images(dto.file()));
        var saved = repository.save(entity);
        return mapper.toDto(saved);
    }

    @Override
    public Optional<ArticleDto> findById(Long id) {
        return repository.findById(id)
                .map(mapper::toDto);
    }

    @Override
    public List<ArticleDto> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public ArticleDto update(Long id, ArticleDto dto) {
        repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Article not found: " + id));

        Articles updated = repository.save(mapper.toEntity(dto));
        return mapper.toDto(updated);
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Article not found: " + id);
        }
        repository.deleteById(id);
    }
}

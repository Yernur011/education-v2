package com.rdlab.education.service.crud.impl;

import com.rdlab.education.domain.dto.article.ArticleDto;
import com.rdlab.education.domain.dto.page.PageableDto;
import com.rdlab.education.domain.entity.edu.Articles;
import com.rdlab.education.domain.entity.edu.Notification;
import com.rdlab.education.domain.entity.image.Base64Images;
import com.rdlab.education.domain.enums.CourseStatus;
import com.rdlab.education.domain.mapper.ArticleMapper;
import com.rdlab.education.domain.repository.edu.ArticlesRepository;
import com.rdlab.education.service.crud.ArticleService;
import com.rdlab.education.service.notification.NotificationService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.rdlab.education.domain.enums.NotificationEntityType.ARTICLE;


@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private final ArticlesRepository repository;

    @Qualifier("articleMapper")
    private final ArticleMapper mapper;
    private final NotificationService notificationService;

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
    public PageableDto<ArticleDto> findAll(int page, int size) {
        Page<Articles> all = repository.findAll(PageRequest.of(page, size));
        List<ArticleDto> list = all.getContent()
                .stream()
                .map(mapper::toDto)
                .toList();
        return new PageableDto<>(all.getTotalPages(), list);
    }

    @Override
    public ArticleDto update(Long id, ArticleDto dto) {
        Articles article = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Article not found: " + id));
        if (CourseStatus.CREATED.equals(article.getStatus())
            && CourseStatus.PUBLISHED.equals(dto.status())) {
            notificationService.createNotification(Notification.builder()
                    .title("Добавлена новая статья")
                    .description("Статья о " + dto.title() + " по ссылке: " + getCurrentUrl("knowledge/article" + id))
                    .entityId(id)
                    .entity(ARTICLE)
                    .build());
        }
        Articles entity = mapper.toEntity(dto);
        entity.setId(id);

        Articles updated = repository.save(entity);
        return mapper.toDto(updated);
    }

    public static String getCurrentUrl(String uri) {
        return "https://rd-lab.com/" + uri + "/";
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Article not found: " + id);
        }
        repository.deleteById(id);
    }
}

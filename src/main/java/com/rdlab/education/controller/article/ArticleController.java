package com.rdlab.education.controller.article;

import com.rdlab.education.domain.dto.article.ArticleDto;
import com.rdlab.education.service.crud.ArticleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.rdlab.education.utils.codes.ProductCode.ARTICLE_URI;
import static com.rdlab.education.utils.codes.ProductCode.V1_URI;

@RestController
@RequiredArgsConstructor
@RequestMapping(V1_URI + ARTICLE_URI)
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping
    public ResponseEntity<List<ArticleDto>> getAll() {
        return ResponseEntity.ok(articleService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArticleDto> getById(@PathVariable Long id) {
        return articleService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ArticleDto> create(@Valid @RequestBody ArticleDto dto) {
        return ResponseEntity.ok(articleService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArticleDto> update(@PathVariable Long id, @Valid @RequestBody ArticleDto dto) {
        return ResponseEntity.ok(articleService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        articleService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
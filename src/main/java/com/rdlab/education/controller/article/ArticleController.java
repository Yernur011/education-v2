package com.rdlab.education.controller.article;

import com.rdlab.education.domain.dto.article.ArticleDto;
import com.rdlab.education.domain.dto.page.PageableDto;
import com.rdlab.education.service.crud.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.rdlab.education.utils.codes.ProductCode.ARTICLE_URI;
import static com.rdlab.education.utils.codes.ProductCode.V1_URI;

@RestController
@RequiredArgsConstructor
@RequestMapping(V1_URI + ARTICLE_URI)
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping
    public ResponseEntity<PageableDto<ArticleDto>> getAll(@RequestParam int page, @RequestParam int size) {
        return ResponseEntity.ok(articleService.findAll(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArticleDto> getById(@PathVariable Long id) {
        return articleService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
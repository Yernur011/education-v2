package com.rdlab.education.controller.forum;

import com.rdlab.education.domain.dto.forum.CreateQuestionDto;
import com.rdlab.education.domain.dto.forum.GetForums;
import com.rdlab.education.domain.dto.page.PageableDto;
import com.rdlab.education.domain.entity.edu.ForumCategory;
import com.rdlab.education.service.crud.ForumCategoryService;
import com.rdlab.education.service.crud.ForumCrudService;
import com.rdlab.education.service.crud.ForumService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.rdlab.education.utils.codes.ProductCode.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(V1_URI + FORUMS_URI)
public class ForumController {
    private final ForumCategoryService forumCategoryService;
    private final ForumCrudService forumService;

    @PostMapping
    public ResponseEntity<Void> createForum(@RequestBody CreateQuestionDto createQuestionDto) {
        forumService.createForumQuestion(createQuestionDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/category")
    public ResponseEntity<List<ForumCategory>> getForumCategories() {
        return ResponseEntity.ok(forumCategoryService.findAll());
    }

    @GetMapping
    public ResponseEntity<PageableDto<GetForums>> getForums(@RequestParam Integer page, @RequestParam Integer size) {
        return ResponseEntity.ok(forumService.forumQuestions(page, size));
    }
    @GetMapping("/by-forum-category")
    public ResponseEntity<PageableDto<GetForums>> getForumCategory(@RequestParam Integer page, @RequestParam Integer size, @RequestParam Long forumCategoryId) {
        return ResponseEntity.ok(forumService.getForumByCategory(page, size, forumCategoryId));
    }

    //TODO написать логику опубликовывания ответов
    @GetMapping("/{id}")
    public ResponseEntity<Object> getForum(@PathVariable Long id) {
        return null;
    }

    @GetMapping("/{id}" + USERS_URI)
    public ResponseEntity<Object> getForumUsers(@PathVariable Long id) {
        return null;
    }

    @PutMapping
    public ResponseEntity<Object> updateForum(@RequestBody Object forum) {
        return null;
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteForum(@RequestBody Object forum) {
        return null;
    }
}

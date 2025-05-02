package com.rdlab.education.controller.forum;

import com.rdlab.education.domain.dto.forum.*;
import com.rdlab.education.domain.dto.page.PageableDto;
import com.rdlab.education.service.crud.ForumCategoryService;
import com.rdlab.education.service.crud.ForumCrudService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.rdlab.education.utils.codes.ProductCode.FORUMS_URI;
import static com.rdlab.education.utils.codes.ProductCode.V1_URI;

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
    public ResponseEntity<List<ForumCategoryDto>> getForumCategories() {
        return ResponseEntity.ok(forumCategoryService.findAll());
    }

    @GetMapping
    public ResponseEntity<PageableDto<GetForums>> getForums(@RequestParam Integer page, @RequestParam Integer size) {
        return ResponseEntity.ok(forumService.forumQuestions(page, size));
    }

    @GetMapping("{id}")
    public ResponseEntity<GetForumWithAnswers> getForums(@PathVariable Integer id) {
        return ResponseEntity.ok(forumService.forumQuestionWithAnswers(Long.valueOf(id), true));
    }

    @GetMapping("/by-forum-category")
    public ResponseEntity<PageableDto<GetForums>> getForumCategory(@RequestParam Integer page, @RequestParam Integer size, @RequestParam Long topicId) {
        return ResponseEntity.ok(forumService.getForumByCategory(page, size, topicId));
    }

    @PostMapping("/like/{id}") //question id
    public ResponseEntity<Integer> likeQuestion(@PathVariable Long id) {
        return ResponseEntity.ok(forumService.likeQuestion(id));
    }

    @PostMapping("/answer/{id}")
    public ResponseEntity<ForumAnswerDto> answerQuestion(@PathVariable Long id, @RequestBody ForumAnswerDto forumAnswerDto) {
        return ResponseEntity.ok(forumService.addAnswer(forumAnswerDto, id));
    }

    @GetMapping("/answer/history")
    public ResponseEntity<PageableDto<ForumAnswerDto>> answerQuestionHistory(@RequestParam Integer page, @RequestParam Integer size) {
        return ResponseEntity.ok(forumService.getAnswersHistory(page, size));
    }

    @GetMapping("history")
    ResponseEntity<PageableDto<GetForums>> getForumsHistory(@RequestParam Integer page, @RequestParam Integer size) {
        return ResponseEntity.ok(forumService.forumQuestionsHistory(page, size));
    }
}

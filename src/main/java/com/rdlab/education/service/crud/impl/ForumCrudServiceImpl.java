package com.rdlab.education.service.crud.impl;

import com.rdlab.education.domain.dto.forum.CreateQuestionDto;
import com.rdlab.education.domain.dto.forum.GetForums;
import com.rdlab.education.domain.dto.page.PageableDto;
import com.rdlab.education.domain.entity.edu.ForumCategory;
import com.rdlab.education.domain.entity.edu.ForumQuestion;
import com.rdlab.education.domain.entity.image.Base64Images;
import com.rdlab.education.domain.enums.ForumQuestionState;
import com.rdlab.education.domain.exceptions.ApiException;
import com.rdlab.education.domain.repository.edu.ForumCategoryRepository;
import com.rdlab.education.domain.repository.edu.ForumQuestionRepository;
import com.rdlab.education.service.auth.UserService;
import com.rdlab.education.service.crud.ForumCrudService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ForumCrudServiceImpl implements ForumCrudService {
    private final ForumQuestionRepository forumQuestionRepository;
    private final ForumCategoryRepository forumCategoryRepository;
    private final UserService userService;

    @Override
    public void createForumQuestion(CreateQuestionDto questionDto) {
        Optional<ForumCategory> byId = forumCategoryRepository.findById(questionDto.category());
        ForumQuestion forumQuestion = new ForumQuestion();
        forumQuestion.setCategory(byId.orElseThrow(() -> new ApiException("Нет такой категоии!")));
        forumQuestion.setAuthor(userService.getCurrentUser());
        forumQuestion.setQuestionText(questionDto.text());
        forumQuestion.setTitle(questionDto.title());
        forumQuestion.setImages(new Base64Images(questionDto.base64Image()));
        forumQuestion.setStatus(ForumQuestionState.CREATED.getState());
        forumQuestionRepository.save(forumQuestion);
    }

    @Override
    public PageableDto<GetForums> forumQuestions(int page, int size) {
        Page<ForumQuestion> forumQuestionByStatus =
                forumQuestionRepository.findForumQuestionByStatus(ForumQuestionState.APPROVED.getState(), PageRequest.of(page, size));

        PageableDto<GetForums> getForumsPageableDto = new PageableDto<>();

        List<GetForums> list = forumQuestionByStatus.get()
                .map(forumQuestion ->
                        new GetForums(
                                forumQuestion.getId(),
                                userService.getCurrentUser().getImage().getBase64Image(),
                                forumQuestion.getTitle(),
                                forumQuestion.getCreatedAt(),
                                forumQuestion.getAnswers().size()))
                .toList();

        getForumsPageableDto.setContent(list);
        getForumsPageableDto.setTotalPages(forumQuestionByStatus.getTotalPages());
        return getForumsPageableDto;
    }

    @Override
    public PageableDto<GetForums> getForumByCategory(int page, int size, Long categoryId) {
        ForumCategory forumCategory = forumCategoryRepository.findById(categoryId).orElseThrow(() -> new ApiException("Нет такой категоии!"));

        Page<ForumQuestion> forumQuestionByForumCategoryAndStatus =
                forumQuestionRepository.findForumQuestionByForumCategoryAndStatus(forumCategory, ForumQuestionState.APPROVED.getState(), PageRequest.of(page, size));

        List<GetForums> list = forumQuestionByForumCategoryAndStatus.getContent().stream()
                .map(forumQuestion ->
                        new GetForums(
                                forumQuestion.getId(),
                                userService.getCurrentUser().getImage().getBase64Image(),
                                forumQuestion.getTitle(),
                                forumQuestion.getCreatedAt(),
                                forumQuestion.getAnswers().size()))
                .toList();

        PageableDto<GetForums> getForumsPageableDto = new PageableDto<>();
        getForumsPageableDto.setTotalPages(forumQuestionByForumCategoryAndStatus.getTotalPages());
        getForumsPageableDto.setContent(list);
        return getForumsPageableDto;
    }
}

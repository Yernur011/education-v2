package com.rdlab.education.service.crud.impl;

import com.rdlab.education.domain.dto.forum.CreateQuestionDto;
import com.rdlab.education.domain.dto.forum.ForumAnswerDto;
import com.rdlab.education.domain.dto.forum.GetForumWithAnswers;
import com.rdlab.education.domain.dto.forum.GetForums;
import com.rdlab.education.domain.dto.page.PageableDto;
import com.rdlab.education.domain.dto.user.info.UserInfoOutputDto;
import com.rdlab.education.domain.entity.auth.Users;
import com.rdlab.education.domain.entity.edu.ForumAnswers;
import com.rdlab.education.domain.entity.edu.ForumCategory;
import com.rdlab.education.domain.entity.edu.ForumLikes;
import com.rdlab.education.domain.entity.edu.ForumQuestion;
import com.rdlab.education.domain.entity.image.Base64Images;
import com.rdlab.education.domain.enums.ForumQuestionState;
import com.rdlab.education.domain.exceptions.ApiException;
import com.rdlab.education.domain.repository.edu.AnswerRepository;
import com.rdlab.education.domain.repository.edu.ForumAnswerRepository;
import com.rdlab.education.domain.repository.edu.ForumCategoryRepository;
import com.rdlab.education.domain.repository.edu.ForumLikesRepository;
import com.rdlab.education.domain.repository.edu.ForumQuestionRepository;
import com.rdlab.education.service.auth.UserService;
import com.rdlab.education.service.crud.ForumCrudService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ForumCrudServiceImpl implements ForumCrudService {
    private final ForumQuestionRepository forumQuestionRepository;
    private final ForumCategoryRepository forumCategoryRepository;
    private final ForumAnswerRepository forumAnswerRepository;
    private final UserService userService;
    private final ForumLikesRepository likesRepository;

    @Override
    public void createForumQuestion(CreateQuestionDto questionDto) {
        Optional<ForumCategory> byId = forumCategoryRepository.findById(questionDto.category());
        ForumQuestion forumQuestion = new ForumQuestion();
        forumQuestion.setCategory(byId.orElseThrow(() -> new ApiException("Нет такой категоии!")));
        forumQuestion.setAuthor(userService.getCurrentUser());
        forumQuestion.setQuestionText(questionDto.text());
        forumQuestion.setTitle(questionDto.title());
        if (questionDto.base64Image() != null) {
            forumQuestion.setImages(new Base64Images(questionDto.base64Image()));
        }
        forumQuestion.setStatus(ForumQuestionState.APPROVED.getState());
        forumQuestionRepository.save(forumQuestion);
    }

    @Override
    public PageableDto<GetForums> forumQuestions(int page, int size) {
        return getGetForumsPageableDto(forumQuestionRepository
                .findForumQuestionByStatus(ForumQuestionState.APPROVED.getState(), PageRequest.of(page, size, Sort.by("id").descending())));
    }

    @Override
    public PageableDto<GetForums> getForumByCategory(int page, int size, Long categoryId) {
        ForumCategory forumCategory = forumCategoryRepository.findById(categoryId).orElseThrow(() -> new ApiException("Нет такой категоии!"));

        Page<ForumQuestion> forumQuestionByForumCategoryAndStatus =
                forumQuestionRepository.findForumQuestionByCategoryAndStatus(forumCategory, ForumQuestionState.APPROVED.getState(), PageRequest.of(page, size, Sort.by("id").descending()));

        List<GetForums> list = forumQuestionByForumCategoryAndStatus.getContent().stream()
                .map(forumQuestion ->
                        new GetForums(
                                forumQuestion.getId(),
                                forumQuestion.getAuthor().getImage() == null ? "" : forumQuestion.getAuthor().getImage().getBase64Image(),
                                forumQuestion.getTitle(),
                                forumQuestion.getCreatedAt(),
                                forumQuestion.getAnswers().size(),
                                likesRepository.countByForumId(forumQuestion.getId())))
                .toList();

        PageableDto<GetForums> getForumsPageableDto = new PageableDto<>();
        getForumsPageableDto.setTotalPages(forumQuestionByForumCategoryAndStatus.getTotalPages());
        getForumsPageableDto.setContent(list);
        return getForumsPageableDto;
    }

    @Override
    public PageableDto<GetForums> forumQuestionsHistory(Integer page, Integer size) {
        return getGetForumsPageableDto(forumQuestionRepository
                .findForumQuestionByAuthor(userService.getCurrentUser(), PageRequest.of(page, size, Sort.by("id").descending())));
    }

    private PageableDto<GetForums> getGetForumsPageableDto(Page<ForumQuestion> forumQuestionsHistory) {
        PageableDto<GetForums> getForumsPageableDto = new PageableDto<>();

        List<GetForums> list = forumQuestionsHistory.stream()
                .map(forumQuestion ->
                        new GetForums(
                                forumQuestion.getId(),
                                forumQuestion.getAuthor().getImage() == null ? "" : forumQuestion.getAuthor().getImage().getBase64Image(),
                                forumQuestion.getTitle(),
                                forumQuestion.getCreatedAt(),
                                forumQuestion.getAnswers().size(),
                                likesRepository.countByForumId(forumQuestion.getId())
                        ))
                .toList();

        getForumsPageableDto.setContent(list);
        getForumsPageableDto.setTotalPages(forumQuestionsHistory.getTotalPages());
        return getForumsPageableDto;
    }

    @Override
    public Integer likeQuestion(Long id) {
        Users currentUser = userService.getCurrentUser();
        Long userId = currentUser.getId();
        if (likesRepository.existsByUserIdAndForumId(userId, id)) {
            throw new ApiException("У пользователя есть лайк!");
        }
        ForumLikes forumLikes = new ForumLikes();
        forumLikes.setForumId(id);
        forumLikes.setUserId(userId);
        likesRepository.save(forumLikes);
        return likesRepository.countByForumId(id);
    }

    @Override
    public ForumAnswerDto addAnswer(ForumAnswerDto forumAnswerDto, Long forumId) {
        Users currentUser = userService.getCurrentUser();
        ForumQuestion forumQuestion = forumQuestionRepository.findById(forumId)
                .orElseThrow(() -> new ApiException("Нет такого Форума!"));
        ForumAnswers forumAnswers = new ForumAnswers();
        forumAnswers.setUser(currentUser);
        forumAnswers.setAnswerText(forumAnswerDto.text());
        forumAnswers.setQuestion(forumQuestion);
        forumAnswerRepository.saveAndFlush(forumAnswers);
        return forumAnswerDto;
    }

    @Override
    public GetForumWithAnswers forumQuestionWithAnswers(Long id, boolean answerListNeeded) {
        ForumQuestion forumQuestion = forumQuestionRepository.findById(id)
                .orElseThrow(() -> new ApiException("Нет такого Форума!"));
        GetForumWithAnswers getForumWithAnswers = new GetForumWithAnswers();
        getForumWithAnswers.setAnswerCount(forumQuestion.getAnswers().size());
        getForumWithAnswers.setLikes(likesRepository.countByForumId(forumQuestion.getId()));
        getForumWithAnswers.setQuestionId(forumQuestion.getId());
        getForumWithAnswers.setCreatedAt(forumQuestion.getCreatedAt());
        getForumWithAnswers.setForumTitle(forumQuestion.getTitle());
        getForumWithAnswers.setForumImage(forumQuestion.getImages().getBase64Image());
        getForumWithAnswers.setAuthorName(forumQuestion.getAuthor().getName());
        getForumWithAnswers.setAuthorImage(forumQuestion.getAuthor().getImage().getBase64Image());
        getForumWithAnswers.setText(forumQuestion.getQuestionText());

        if (answerListNeeded)
            getForumWithAnswers.setAnswersList(forumQuestion.getAnswers()
                    .stream().map(forumAnswer -> new ForumAnswerDto(
                            forumAnswer.getAnswerText(),
                            forumAnswer.getCreatedDate(),
                            new UserInfoOutputDto(forumAnswer.getUser().getName(), forumAnswer.getUser().getLastname(), forumAnswer.getUser().getImage().getBase64Image())
                            , null
                    ))
                    .toList());
        return getForumWithAnswers;
    }

    @Override
    public PageableDto<ForumAnswerDto> getAnswersHistory(Integer page, Integer size) {
        Page<ForumAnswers> byUser = forumAnswerRepository.findByUser(userService.getCurrentUser(), PageRequest.of(page, size));
        List<ForumAnswerDto> answers = byUser.getContent().stream()
                .map(answer -> new ForumAnswerDto(
                        answer.getAnswerText(),
                        answer.getCreatedDate(),
                        null,
                        forumQuestionWithAnswers(answer.getQuestion().getId(), false)
                )).toList();

        PageableDto<ForumAnswerDto> getForumsAnswerDto = new PageableDto<>();
        getForumsAnswerDto.setTotalPages(byUser.getTotalPages());
        getForumsAnswerDto.setContent(answers);
        return getForumsAnswerDto;

    }
}

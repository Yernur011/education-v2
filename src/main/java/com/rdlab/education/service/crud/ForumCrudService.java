package com.rdlab.education.service.crud;

import com.rdlab.education.domain.dto.forum.*;
import com.rdlab.education.domain.dto.page.PageableDto;

public interface ForumCrudService {
    void createForumQuestion(CreateQuestionDto questionDto);

    void approveForumQuestion(Long id);

    void revokeForumQuestion(Long id);

    void removeForumQuestion(Long id);

    PageableDto<GetForums> forumQuestions(int page, int size);

    PageableDto<GetForums> getForumByCategory(int page, int size, Long categoryId);

    PageableDto<GetForums> forumQuestionsHistory(Integer page, Integer size);

    Integer likeQuestion(Long id);

    ForumAnswerDto addAnswer(ForumAnswerDto forumAnswerDto, Long forumId);

    GetForumWithAnswers forumQuestionWithAnswers(Long id, boolean answerListNeeded);

    PageableDto<ForumAnswerDto> getAnswersHistory(Integer page, Integer size);

    PageableDto<AdminForum> getAllForum(int page, int size);
}

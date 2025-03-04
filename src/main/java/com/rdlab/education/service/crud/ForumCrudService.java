package com.rdlab.education.service.crud;

import com.rdlab.education.domain.dto.forum.CreateQuestionDto;
import com.rdlab.education.domain.dto.forum.ForumAnswerDto;
import com.rdlab.education.domain.dto.forum.GetForumWithAnswers;
import com.rdlab.education.domain.dto.forum.GetForums;
import com.rdlab.education.domain.dto.page.PageableDto;

import java.util.List;

public interface ForumCrudService {
    void createForumQuestion(CreateQuestionDto questionDto);
    PageableDto<GetForums> forumQuestions(int page, int size);
    PageableDto<GetForums> getForumByCategory(int page, int size, Long categoryId);
    PageableDto<GetForums> forumQuestionsHistory(Integer page, Integer size);
    Integer likeQuestion(Long id);
    ForumAnswerDto addAnswer(ForumAnswerDto forumAnswerDto, Long forumId);
    GetForumWithAnswers forumQuestionWithAnswers(Long id, boolean answerListNeeded);
    PageableDto<ForumAnswerDto> getAnswersHistory(Integer page, Integer size);
}

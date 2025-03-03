package com.rdlab.education.service.crud;

import com.rdlab.education.domain.dto.forum.CreateQuestionDto;
import com.rdlab.education.domain.dto.forum.ForumAnswerDto;
import com.rdlab.education.domain.dto.forum.GetForums;
import com.rdlab.education.domain.dto.page.PageableDto;

public interface ForumCrudService {
    void createForumQuestion(CreateQuestionDto questionDto);
    PageableDto<GetForums> forumQuestions(int page, int size);
    PageableDto<GetForums> getForumByCategory(int page, int size, Long categoryId);
    Integer likeQuestion(Long id);

    ForumAnswerDto addAnswer(ForumAnswerDto forumAnswerDto, Long forumId);
}

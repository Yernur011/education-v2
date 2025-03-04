package com.rdlab.education.domain.dto.forum;

import com.rdlab.education.domain.entity.edu.ForumAnswers;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GetForumWithAnswers {
    Long questionId;
    String authorImage;
    String authorName;
    String forumImage;
    String forumTitle;
    LocalDateTime createdAt;
    Integer answerCount;
    List<ForumAnswerDto> answersList;
    Integer likes;
}

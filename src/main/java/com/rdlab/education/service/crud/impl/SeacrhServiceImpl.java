package com.rdlab.education.service.crud.impl;

import com.rdlab.education.domain.dto.SearchDto;
import com.rdlab.education.domain.repository.edu.CourseRepository;
import com.rdlab.education.domain.repository.edu.ForumAnswerRepository;
import com.rdlab.education.domain.repository.edu.ForumQuestionRepository;
import com.rdlab.education.service.crud.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SeacrhServiceImpl implements SearchService {

    private final ForumQuestionRepository forumQuestionRepository;
    private final CourseRepository courseRepository;
    private final ForumAnswerRepository forumAnswerRepository;


    @Override
    public List<SearchDto> search(String query) {
        List<SearchDto> searchDtoList = new ArrayList<>();

        forumQuestionRepository
                .findByTitleContainingIgnoreCaseOrQuestionTextContainingIgnoreCase(query, query)
                .forEach(q -> {
                            SearchDto dto = new SearchDto();
                            dto.setText(q.getTitle());
                            dto.setReferenceId(q.getId());
                            dto.setType("forums");
                            searchDtoList.add(dto);
                        }
                );

        courseRepository.findByTitleContainingIgnoreCase(query)
                .forEach(course -> {
                    SearchDto dto = new SearchDto();
                    dto.setText(course.getTitle());
                    dto.setReferenceId(course.getId());
                    dto.setType("courses");
                    searchDtoList.add(dto);
                });

        forumAnswerRepository.findByAnswerTextContainingIgnoreCase(query)
                .forEach(comment -> {
                    SearchDto dto = new SearchDto();
                    dto.setText(comment.getAnswerText());
                    dto.setReferenceId(comment.getQuestion().getId());
                    dto.setType("comments");
                    searchDtoList.add(dto);
                });

        return searchDtoList;

    }
}

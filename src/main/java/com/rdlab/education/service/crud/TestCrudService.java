package com.rdlab.education.service.crud;

import com.rdlab.education.domain.dto.page.PageableDto;
import com.rdlab.education.domain.dto.test.TestCreateDto;
import com.rdlab.education.domain.dto.test.TestDto;
import com.rdlab.education.domain.entity.edu.Test;

import java.util.List;
import java.util.Optional;

public interface TestCrudService {
    PageableDto<TestDto> findAllTest(Long page, Long size);

    PageableDto<TestDto> findAllTestDto(Long page, Long size);

    PageableDto<TestDto> findCompletedTestDto(Long page, Long size);

    TestDto findDtoById(Long id);

    Optional<Test> findById(Long id);
    TestDto save(TestCreateDto test);

    TestDto update(TestCreateDto test);

    TestCreateDto getTestDetails(Long id);

    void deleteTest(Long id);

    void deleteQuestion(Long testId, Long questionId);

    void deleteAnswer(Long questionId, Long answerId);
}

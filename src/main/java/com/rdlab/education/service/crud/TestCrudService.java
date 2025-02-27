package com.rdlab.education.service.crud;

import com.rdlab.education.domain.dto.page.PageableDto;
import com.rdlab.education.domain.dto.test.TestDto;
import com.rdlab.education.domain.entity.edu.Test;

import java.util.List;
import java.util.Optional;

public interface TestCrudService {
    List<TestDto> findAllTest(Long page, Long size);
    PageableDto<TestDto> findAllTestDto(Long page, Long size);

    TestDto findDtoById(Long id);

    Optional<Test> findById(Long id);



}

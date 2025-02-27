package com.rdlab.education.service.crud.impl;

import com.rdlab.education.domain.dto.page.PageableDto;
import com.rdlab.education.domain.dto.test.TestDto;
import com.rdlab.education.domain.entity.edu.Test;
import com.rdlab.education.domain.enums.UserTestStatusEnum;
import com.rdlab.education.domain.repository.edu.TestRepository;
import com.rdlab.education.service.business.logic.CourseService;
import com.rdlab.education.service.business.logic.impl.CourseServiceImpl;
import com.rdlab.education.service.crud.TestCrudService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static com.rdlab.education.utils.codes.ErrorCode.TEST_NOT_FOUND;


@Service
@RequiredArgsConstructor
public class TestCrudServiceImpl implements TestCrudService {
    private final TestRepository testRepository;
    private final CourseService courseService;

    @Override
    public List<TestDto> findAllTest(Long page, Long size) {
        return testRepository.findAll(PageRequest.of(page.intValue(), size.intValue()))
                .getContent()
                .stream()
                .map(test ->
                        new TestDto(
                                test.getId(),
                                test.getTitle(),
                                test.getState(),
                                test.getType(),
                                test.getCourse().getId()
                        )
                )
                .toList();
    }

    @Override
    public PageableDto<TestDto> findAllTestDto(Long page, Long size) {
        PageableDto<TestDto> pageableDto = new PageableDto<>();
        Page<Test> all = testRepository.findAll(PageRequest.of(page.intValue(), size.intValue()));

        pageableDto.setTotalPages(all.getTotalPages());
        pageableDto.setContent(all.getContent()
                .stream()
                .map(test -> courseService.getCurrentTestByCourse(test.getCourse().getId()))
                .toList());

        return pageableDto;
    }

    @Override
    public TestDto findDtoById(Long id) {
        return testRepository.findById(id)
                .map(test -> courseService.getCurrentTestByCourse(test.getCourse().getId()))
                .orElseThrow(() -> new NoSuchElementException(TEST_NOT_FOUND));
    }

    @Override
    public Optional<Test> findById(Long id) {
        return testRepository.findById(id);
    }


}

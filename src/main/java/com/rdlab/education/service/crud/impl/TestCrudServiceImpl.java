package com.rdlab.education.service.crud.impl;

import com.rdlab.education.domain.dto.test.TestDto;
import com.rdlab.education.domain.entity.edu.Test;
import com.rdlab.education.domain.repository.edu.TestRepository;
import com.rdlab.education.service.crud.TestCrudService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static com.rdlab.education.utils.codes.ErrorCode.TEST_NOT_FOUND;


@Service
public class TestCrudServiceImpl implements TestCrudService {
    private final TestRepository testRepository;

    public TestCrudServiceImpl(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

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
    public TestDto findDtoById(Long id) {
        return testRepository.findById(id)
                .map(test ->
                        new TestDto(
                                test.getId(),
                                test.getTitle(),
                                test.getState(),
                                test.getType(),
                                test.getCourse().getId()
                        )
                ).orElseThrow(() -> new NoSuchElementException(TEST_NOT_FOUND));
    }

    @Override
    public Optional<Test> findById(Long id) {
        return testRepository.findById(id);
    }


}

package com.rdlab.education.service.crud.impl;

import com.rdlab.education.domain.dto.course.CourseDetailsDto;
import com.rdlab.education.domain.dto.course.CoursesResponseDto;
import com.rdlab.education.domain.dto.test.TestDto;
import com.rdlab.education.domain.entity.edu.Course;
import com.rdlab.education.domain.entity.edu.Tags;
import com.rdlab.education.domain.exceptions.InvalidValueException;
import com.rdlab.education.domain.repository.edu.CourseRepository;
import com.rdlab.education.domain.repository.edu.LessonRepository;
import com.rdlab.education.domain.repository.edu.TestRepository;
import com.rdlab.education.service.business.logic.CourseService;
import com.rdlab.education.service.crud.CourseCrudService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static com.rdlab.education.utils.codes.ErrorCode.COURSE_NOT_FOUND;
import static com.rdlab.education.utils.codes.ErrorCode.UPDATE_COURSE_EXCEPTION;


@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CourseCrudServiceImpl implements CourseCrudService {
    private final CourseRepository courseRepository;

    private final LessonRepository lessonRepository;

    private final TestRepository testRepository;

    private final CourseService courseService;

    @Override
    public List<CoursesResponseDto> findAll(Long page, Long size) {
        return courseRepository.findAll(PageRequest.of(page.intValue(), size.intValue()))
                .getContent()
                .stream()
                .map(course ->
                        new CoursesResponseDto(
                                course.getId(),
                                course.getBase64Images().getBase64Image(),
                                course.getTags().stream().map(Tags::getName).toList(),
                                course.getTitle(),
                                course.getDescription()))
                .toList();
    }

    @Override
    public List<Course> findAll() {
        return courseRepository.findAll();
    }


    public CourseDetailsDto findByIdAndProgress(Long id) {
        return courseService.findByIdAndProgress(id);
    }

    @Override
    public CourseDetailsDto findById(Long id) {
        return courseRepository.findById(id)
                .map(course ->
                        new CourseDetailsDto(
                                course.getId(),
                                course.getTitle(),
                                course.getDescription(),
                                course.getBase64Images().getBase64Image(),
                                course.getTags().stream().map(Tags::getName).toList(),
                                lessonRepository.findAllByCourseId(course.getId()),
                                testRepository.findById(course.getTest().getId())
                                        .map(test -> new TestDto(
                                                        test.getId(),
                                                        test.getTitle(),
                                                        test.getState(),
                                                        test.getType(),
                                                        course.getId()
                                                )
                                        )
                                        .orElse(null)
                        )
                )
                .orElseThrow(() -> new NoSuchElementException(COURSE_NOT_FOUND));
    }

    @Override
    public Course save(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public Course update(Course course) {
        return Optional.of(course.getId())
                .map(id -> save(course))
                .orElseThrow(() -> new InvalidValueException(UPDATE_COURSE_EXCEPTION));
    }

    @Override
    public void deleteById(Long id) {
        courseRepository.deleteById(id);
    }
}

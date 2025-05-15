package com.legalDigital.education.service.crud.impl;

import com.legalDigital.education.domain.dto.course.AdminCourseResponse;
import com.legalDigital.education.domain.dto.course.CourseDetailsDto;
import com.legalDigital.education.domain.dto.course.CoursesResponseDto;
import com.legalDigital.education.domain.dto.lesson.LessonDto;
import com.legalDigital.education.domain.dto.page.PageableDto;
import com.legalDigital.education.domain.entity.edu.Course;
import com.legalDigital.education.domain.entity.edu.Tags;
import com.legalDigital.education.domain.entity.edu.UserCourse;
import com.legalDigital.education.domain.enums.UserCourseLessonStatusEnum;
import com.legalDigital.education.domain.exceptions.InvalidValueException;
import com.legalDigital.education.domain.repository.edu.CourseRepository;
import com.legalDigital.education.domain.repository.edu.UserCourseRepository;
import com.legalDigital.education.service.auth.UserService;
import com.legalDigital.education.service.business.logic.CourseService;
import com.legalDigital.education.service.crud.CourseCrudService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.legalDigital.education.utils.codes.ErrorCode.COURSE_NOT_FOUND;
import static com.legalDigital.education.utils.codes.ErrorCode.UPDATE_COURSE_EXCEPTION;


@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CourseCrudServiceImpl implements CourseCrudService {
    private final CourseRepository courseRepository;
    private final UserCourseRepository userCourseRepository;
    private final CourseService courseService;
    private final UserService userService;

    @Override
    public PageableDto<CoursesResponseDto> findAllPageable(Long page, Long size) {
        Page<Course> all = courseRepository.findAllByStatus(UserCourseLessonStatusEnum.ACTIVE.getStatus(), PageRequest.of(page.intValue(), size.intValue()));
        PageableDto<CoursesResponseDto> coursesResponseDtoPageableDto = new PageableDto<>();
        coursesResponseDtoPageableDto.setTotalPages(all.getTotalPages());
        coursesResponseDtoPageableDto.setContent(all.getContent().stream()
                .map(course ->
                        new CoursesResponseDto(
                                course.getId(),
                                course.getBase64Images().getBase64Image(),
                                course.getTags().stream().map(Tags::getName).toList(),
                                course.getTitle(),
                                course.getDescription(),
                                course.getStatus()
                        )
                ).toList());

        return coursesResponseDtoPageableDto;
    }

    @Override
    public PageableDto<AdminCourseResponse> findAll(Long page, Long size) {
        Page<Course> all = courseRepository.findAll(PageRequest.of(page.intValue(), size.intValue()));
        PageableDto<AdminCourseResponse> coursesResponseDtoPageableDto = new PageableDto<>();
        coursesResponseDtoPageableDto.setTotalPages(all.getTotalPages());
        coursesResponseDtoPageableDto.setContent(
                all.getContent()
                        .stream()
                        .map(course -> {
                            AdminCourseResponse adminCourseResponse = new AdminCourseResponse();
                            adminCourseResponse.setId(course.getId());
                            adminCourseResponse.setTags(course.getTags().stream().map(Tags::getName).toList());
                            adminCourseResponse.setTitle(course.getTitle());
                            adminCourseResponse.setCreatedAt(course.getCreationDate());
                            adminCourseResponse.setStatus(course.getStatus());
                            return adminCourseResponse;
                        }).toList()
        );

        return coursesResponseDtoPageableDto;
    }

    @Override
    public PageableDto<CoursesResponseDto> getCouresHistory(Long page, Long size) {
        Page<UserCourse> all = userCourseRepository.findByUserAndStatus(
                userService.getCurrentUser(),
                UserCourseLessonStatusEnum.COMPLETED.getStatus(),
                PageRequest.of(page.intValue(), size.intValue()));
        PageableDto<CoursesResponseDto> coursesResponseDtoPageableDto = new PageableDto<>();
        coursesResponseDtoPageableDto.setTotalPages(all.getTotalPages());
        coursesResponseDtoPageableDto.setContent(all.getContent().stream()
//                .map(UserCourse::getCourse)
                .map(userCourse -> {
                            Course course = userCourse.getCourse();
                            return new CoursesResponseDto(
                                    course.getId(),
                                    course.getBase64Images().getBase64Image(),
                                    course.getTags().stream().map(Tags::getName).toList(),
                                    course.getTitle(),
                                    course.getDescription(),
                                    userCourse.getStatus()
                            );
                        }
                ).toList());
        return coursesResponseDtoPageableDto;
    }

    @Override
    public CourseDetailsDto findByIdAndProgress(Long id) {
        return courseService.findByIdAndProgress(id);
    }

    @Override
    public CourseDetailsDto findByIdWithoutProgress(Long id) {
        return courseService.findByIdWithoutProgress(id);
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
                                course.getLessons().stream()
                                        .map(lesson -> new LessonDto(
                                                lesson.getId(), lesson.getLessonNumber(), lesson.getTitle(), lesson.getVideoUrl(),
                                                lesson.getBodyText(), lesson.getStatus(), lesson.getIsCompleted())
                                        ).collect(Collectors.toList()),
                                null,
                                course.getStatus()
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

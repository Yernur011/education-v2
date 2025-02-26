package com.rdlab.education.service.business.logic.impl;

import com.rdlab.education.domain.dto.course.CourseDetailsDto;
import com.rdlab.education.domain.dto.lesson.LessonDto;
import com.rdlab.education.domain.dto.test.TestDto;
import com.rdlab.education.domain.entity.edu.Lesson;
import com.rdlab.education.domain.entity.edu.Tags;
import com.rdlab.education.domain.entity.edu.UserCourse;
import com.rdlab.education.domain.entity.edu.UserCourseLesson;
import com.rdlab.education.domain.enums.UserCourseLessonStatusEnum;
import com.rdlab.education.domain.enums.UserTestStatusEnum;
import com.rdlab.education.domain.exceptions.NotFoundException;
import com.rdlab.education.domain.repository.edu.CourseRepository;
import com.rdlab.education.domain.repository.edu.LessonRepository;
import com.rdlab.education.domain.repository.edu.UserCourseLessonRepository;
import com.rdlab.education.domain.repository.edu.UserCourseRepository;
import com.rdlab.education.service.auth.UserService;
import com.rdlab.education.service.business.logic.CourseService;
import com.rdlab.education.service.crud.TestCrudService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.rdlab.education.utils.codes.ErrorCode.COURSE_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final UserCourseRepository userCourseRepository;
    private final UserCourseLessonRepository userCourseLessonRepository;
    private final LessonRepository lessonRepository;
    private final UserService userService;
    private final TestCrudService testRepository;

    @Override
    public CourseDetailsDto startCourse(Long courseId) {
        var course = courseRepository.findById(courseId)
                .orElseThrow(() -> new NotFoundException("Course Not Found"));
        userCourseRepository.save(new UserCourse(null, userService.getCurrentUser(), course));

        startLesson(course.getLessons()
                .stream()
                .sorted(Comparator.comparing(Lesson::getLessonNumber))
                .limit(1)
                .toList().getFirst().getId());

        return findByIdAndProgress(courseId);
    }


    @Override
    public List<LessonDto> doneLesson(Long lessonId) {
        UserCourseLesson userCourseLesson = userCourseLessonRepository.findByLessonId(lessonId)
                .orElseThrow(() -> new NotFoundException("User -> Lesson Not Found"));
        if (userCourseLesson.getStatus().equals(UserCourseLessonStatusEnum.STARTED.getStatus())) {
            userCourseLesson.setStatus(UserCourseLessonStatusEnum.COMPLETED.getStatus());
            userCourseLessonRepository.save(userCourseLesson);

//            return startLesson(nextLesson(lessonId));
            Long userCourseId = userCourseLesson.getUserCourse().getId();
            return getCurrentLessonsList(userCourseLessonRepository.findByUserCourseId(userCourseId));
        }
        throw new NotFoundException("User -> Lesson Status Not STARTED Before COMPLETED");
    }

    private Long nextLesson(Long lessonId) {
        var lesson = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new NotFoundException("Lesson Not Found"));
        return lesson.getCourse().getLessons().stream()
                .sorted(Comparator.comparing(Lesson::getLessonNumber))
                .dropWhile(n -> n.getLessonNumber() <= lesson.getLessonNumber())
                .findFirst().map(Lesson::getId)
                .orElse(-1L);
    }

    @Override
    public List<LessonDto> startLesson(Long lessonId) {
        // todo if lessonId -1 test ACTIVE from NOT_ACTIVE (it calculates from lessons)
        var lesson = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new NotFoundException("Lesson Not Found"));
        var courseAndUser = userCourseRepository.findByCourseAndUser(lesson.getCourse(), userService.getCurrentUser());

        UserCourseLesson alreadyExists = userCourseLessonRepository.findByLessonIdAndUserCourse(lessonId, courseAndUser);
        if (alreadyExists != null) {
            throw new NotFoundException("Lesson Already Exists");
        }

        userCourseLessonRepository.save(
                new UserCourseLesson(null,
                        findUserCourse(lesson),
                        lesson,
                        UserCourseLessonStatusEnum.STARTED.getStatus()));

        var userCourseLessons = userCourseLessonRepository.findByUserCourseId(courseAndUser.getId());
        return getCurrentLessonsList(userCourseLessons);
    }

    private UserCourse findUserCourse(Lesson lesson) {
        return userCourseRepository.findByCourseAndUser(lesson.getCourse(), userService.getCurrentUser());
    }

    @Override
    public CourseDetailsDto findByIdAndProgress(Long courseId) {
        return courseRepository.findById(courseId)
                .map(course -> {
                            var courseAndUser = userCourseRepository.findByCourseAndUser(course, userService.getCurrentUser());
                            var userCourseLessons = userCourseLessonRepository.findByUserCourseId(courseAndUser.getId());
                            return new CourseDetailsDto(
                                    course.getId(),
                                    course.getTitle(),
                                    course.getDescription(),
                                    course.getBase64Images().getBase64Image(),
                                    course.getTags().stream().map(Tags::getName).toList(),
                                    getCurrentLessonsList(userCourseLessons),
                                    getCurrentTest(userCourseLessons)
                            );

                        }
                )
                .orElseThrow(() -> new NoSuchElementException(COURSE_NOT_FOUND));

    }

    private TestDto getCurrentTest(List<UserCourseLesson> userCourseLesson) {
        var state = UserTestStatusEnum.NOT_ACTIVE.getStatus();
        UserCourseLesson first = userCourseLesson.getFirst();
        if (userCourseLesson.stream()
                    .filter(l -> l.getStatus().equals(UserCourseLessonStatusEnum.COMPLETED.getStatus()))
                    .count()
            == first.getUserCourse().getCourse().getLessons().size()) {
            state = UserTestStatusEnum.ACTIVE.getStatus();
        }

        String finalState = state;
        return testRepository.findById(first.getUserCourse().getCourse().getTest().getId())
                .map(test -> new TestDto(
                                test.getId(),
                                test.getTitle(),
                                finalState,
                                test.getType(),
                                first.getUserCourse().getCourse().getId()
                        )
                )
                .orElse(null);
    }

    private List<LessonDto> getCurrentLessonsList(List<UserCourseLesson> userCourseLesson) {
        List<Lesson> lessonsWithActiveState = userCourseLesson.getFirst().getUserCourse().getCourse().getLessons();
        List<Lesson> lessonsWithStartedState = userCourseLesson.stream()
                .map(l -> {
                    l.getLesson().setStatus(l.getStatus());
                    return l.getLesson();
                }).toList();

        List<LessonDto> combinedLessons = Stream.concat(
                        lessonsWithActiveState.stream(),
                        lessonsWithStartedState.stream()
                ).collect(Collectors.toMap(
                        Lesson::getId,                        // Key by lesson ID
                        lesson -> lesson,                      // Value: Lesson itself
                        (existing, replacement) -> replacement  // If ID duplicates, overwrite with replacement
                )).values().stream()
                .map(lesson -> new LessonDto(
                        lesson.getId(), lesson.getLessonNumber(), lesson.getTitle(), lesson.getVideoUrl(),
                        lesson.getBodyText(), lesson.getStatus(), lesson.getIsCompleted())

                ).collect(Collectors.toList());

        return combinedLessons;
    }


}

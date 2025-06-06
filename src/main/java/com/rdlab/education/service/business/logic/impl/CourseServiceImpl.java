package com.rdlab.education.service.business.logic.impl;

import com.rdlab.education.domain.dto.course.CourseDetailsDto;
import com.rdlab.education.domain.dto.lesson.LessonDto;
import com.rdlab.education.domain.dto.test.TestDto;
import com.rdlab.education.domain.entity.edu.Category;
import com.rdlab.education.domain.entity.edu.Course;
import com.rdlab.education.domain.entity.edu.Lesson;
import com.rdlab.education.domain.entity.edu.Notification;
import com.rdlab.education.domain.entity.edu.Tags;
import com.rdlab.education.domain.entity.edu.UserCourse;
import com.rdlab.education.domain.entity.edu.UserCourseLesson;
import com.rdlab.education.domain.entity.image.Base64Images;
import com.rdlab.education.domain.enums.UserCourseLessonStatusEnum;
import com.rdlab.education.domain.enums.UserTestStatusEnum;
import com.rdlab.education.domain.exceptions.ApiException;
import com.rdlab.education.domain.exceptions.NotFoundException;
import com.rdlab.education.domain.repository.edu.CategoryRepository;
import com.rdlab.education.domain.repository.edu.CourseRepository;
import com.rdlab.education.domain.repository.edu.LessonRepository;
import com.rdlab.education.domain.repository.edu.TagsRepository;
import com.rdlab.education.domain.repository.edu.TestRepository;
import com.rdlab.education.domain.repository.edu.UserCourseLessonRepository;
import com.rdlab.education.domain.repository.edu.UserCourseRepository;
import com.rdlab.education.service.auth.UserService;
import com.rdlab.education.service.business.logic.CourseService;
import com.rdlab.education.service.notification.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.rdlab.education.domain.enums.CourseStatus.CREATED;
import static com.rdlab.education.domain.enums.NotificationEntityType.COURSE;
import static com.rdlab.education.service.crud.impl.ArticleServiceImpl.getCurrentUrl;
import static com.rdlab.education.utils.codes.ErrorCode.COURSE_NOT_FOUND;
import static com.rdlab.education.utils.codes.ProductCode.COURSE_URI;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final UserCourseRepository userCourseRepository;
    private final UserCourseLessonRepository userCourseLessonRepository;
    private final LessonRepository lessonRepository;
    private final TestRepository testRepository;
    private final UserService userService;
    private final TagsRepository tagsRepository;
    private final CategoryRepository categoryRepository;
    private final NotificationService notificationService;

    @Override
    public void testFinished(Long id) {
        testRepository.findById(id)
                .ifPresent(test -> {
                    UserCourse userCourse = getByCourseAndUser(test.getCourse());
                    userCourse.setStatus(UserCourseLessonStatusEnum.COMPLETED.getStatus());
                    userCourseRepository.save(userCourse);
                });
    }

    @Override
    public CourseDetailsDto createCourse(CourseDetailsDto courseDetailsDto) {
        Course course = new Course();
        course.setTitle(courseDetailsDto.getTitle());
        course.setDescription(courseDetailsDto.getDescription());
        course.setBase64Images(new Base64Images(courseDetailsDto.getImage()));
        course.setStatus(CREATED.name());
        course.setDemoUrl(courseDetailsDto.getDemoUrl());

        course.setTags(courseDetailsDto.getTags()
                .stream()
                .map(tagsRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toSet()));

        course.getLessons().addAll(courseDetailsDto.getLessons()
                .stream()
                .map(lessonDto -> {
                    Lesson lesson = new Lesson();
                    lesson.setTitle(lessonDto.getTitle());
                    lesson.setStatus(UserCourseLessonStatusEnum.ACTIVE.getStatus());
                    lesson.setLessonNumber(lessonDto.getLessonNumber());
                    lesson.setVideoUrl(lessonDto.getVideoUrl());
                    lesson.setBodyText(lessonDto.getBodyText());
                    lesson.setIsCompleted(false);
                    lesson.setCourse(course);
                    return lesson;
                })
                .toList());
        course.setCategories(
                courseDetailsDto.getCategories()
                        .stream()
                        .map(categoryRepository::findById)
                        .filter(Optional::isPresent)
                        .map(Optional::get)
                        .collect(Collectors.toSet())
        );

        Course save = courseRepository.save(course);
        CourseDetailsDto courseDetailsDto1 = new CourseDetailsDto();
        courseDetailsDto1.setTitle(save.getTitle());
        courseDetailsDto1.setDescription(save.getDescription());
        courseDetailsDto1.setId(save.getId());
        courseDetailsDto1.setStatus(save.getStatus());
        courseDetailsDto1.setCategories(save.getCategories().stream().map(Category::getId).collect(Collectors.toSet()));
        courseDetailsDto1.setTags(save.getTags().stream().map(Tags::getId).collect(Collectors.toSet()));
        courseDetailsDto1.setImage(course.getBase64Images().getBase64Image());
        courseDetailsDto1.setLessons(
                save.getLessons()
                        .stream()
                        .map(lesson ->
                                new LessonDto(
                                        lesson.getId(),
                                        lesson.getLessonNumber(),
                                        lesson.getTitle(),
                                        lesson.getVideoUrl(),
                                        lesson.getBodyText(),
                                        lesson.getStatus(),
                                        lesson.getIsCompleted()))
                        .toList()
        );
        return courseDetailsDto1;
    }

    @Override
    public Tags saveTags(Tags tags) {
        return tagsRepository.save(tags);
    }

    @Override
    public void deleteTags(Long id) {
        tagsRepository.deleteById(id);
    }

    @Override
    public CourseDetailsDto updateCourse(CourseDetailsDto courseDetailsDto) {
        Optional<Course> byId = courseRepository.findById(courseDetailsDto.getId());
        if (byId.isPresent()) {
            Course course = byId.get();
            course.setTitle(courseDetailsDto.getTitle());
            course.setDemoUrl(courseDetailsDto.getDemoUrl());
            course.setDescription(courseDetailsDto.getDescription());
            if (!course.getBase64Images().getBase64Image().equals(courseDetailsDto.getImage())) {
                course.setBase64Images(new Base64Images(courseDetailsDto.getImage()));
            }

            if ("CREATED".equals(course.getStatus())
                || "ACTIVE".equals(courseDetailsDto.getStatus())) {
                notificationService.createNotification(Notification.builder()
                        .title("Добавлен новый курс")
                        .description("Курс о " + course.getTitle() + " по ссылке: " + getCurrentUrl(COURSE_URI) + course.getId())
                        .entityId(course.getId())
                        .entity(COURSE)
                        .build());
            }
            course.setStatus(courseDetailsDto.getStatus());

            course.setTags(courseDetailsDto.getTags()
                    .stream()
                    .map(tagsRepository::findById)
                    .map(tags -> tags.orElse(null))
                    .collect(Collectors.toSet()));
            course.getLessons().addAll(courseDetailsDto.getLessons()
                    .stream()
                    .map(lessonDto -> lessonRepository.findById(lessonDto.getId())
                            .map(lesson -> {
                                lesson.setTitle(lessonDto.getTitle());
                                lesson.setStatus(UserCourseLessonStatusEnum.ACTIVE.getStatus());
                                lesson.setLessonNumber(lessonDto.getLessonNumber());
                                lesson.setVideoUrl(lessonDto.getVideoUrl());
                                lesson.setBodyText(lessonDto.getBodyText());
                                lesson.setIsCompleted(false);
                                lesson.setCourse(course);
                                return lesson;
                            }).orElseGet(() -> {
                                Lesson lesson = new Lesson();
                                lesson.setTitle(lessonDto.getTitle());
                                lesson.setStatus(UserCourseLessonStatusEnum.ACTIVE.getStatus());
                                lesson.setLessonNumber(lessonDto.getLessonNumber());
                                lesson.setVideoUrl(lessonDto.getVideoUrl());
                                lesson.setBodyText(lessonDto.getBodyText());
                                lesson.setIsCompleted(false);
                                lesson.setCourse(course);
                                return lesson;
                            }))
                    .toList());
            course.setCategories(
                    courseDetailsDto.getCategories()
                            .stream()
                            .map(categoryRepository::findById)
                            .filter(Optional::isPresent)
                            .map(Optional::get)
                            .collect(Collectors.toSet())
            );

            Course save = courseRepository.save(course);
            CourseDetailsDto courseDetailsDto1 = new CourseDetailsDto();
            courseDetailsDto1.setTitle(save.getTitle());
            courseDetailsDto1.setDescription(save.getDescription());
            courseDetailsDto1.setId(save.getId());
            courseDetailsDto1.setCategories(save.getCategories().stream().map(Category::getId).collect(Collectors.toSet()));
            courseDetailsDto1.setStatus(save.getStatus());
            courseDetailsDto1.setTags(save.getTags().stream().map(Tags::getId).collect(Collectors.toSet()));
            courseDetailsDto1.setImage(course.getBase64Images().getBase64Image());
            courseDetailsDto1.setLessons(
                    save.getLessons()
                            .stream()
                            .map(lesson ->
                                    new LessonDto(
                                            lesson.getId(),
                                            lesson.getLessonNumber(),
                                            lesson.getTitle(),
                                            lesson.getVideoUrl(),
                                            lesson.getBodyText(),
                                            lesson.getStatus(),
                                            lesson.getIsCompleted()))
                            .toList()
            );
            return courseDetailsDto1;
        }
        throw new ApiException("Course Not Found");
    }

    @Override
    public LessonDto updateLesson(Long courseId, LessonDto lessonDto) {
        Lesson save = lessonRepository.save(
                new Lesson(
                        lessonDto.getId(),
                        lessonDto.getLessonNumber(),
                        lessonDto.getTitle(),
                        lessonDto.getVideoUrl(),
                        lessonDto.getBodyText(),
                        lessonDto.getStatus(),
                        lessonDto.getIsCompleted(),
                        courseRepository.findById(courseId).orElseThrow(() -> new ApiException("Invalid Not Found")))
        );
        return new LessonDto(
                save.getId(),
                save.getLessonNumber(),
                save.getTitle(),
                save.getVideoUrl(),
                save.getBodyText(),
                save.getStatus(),
                save.getIsCompleted());

    }

    @Override
    public void deleteLesson(Long lessonId) {
        lessonRepository.deleteById(lessonId);
    }

    @Override
    public CourseDetailsDto startCourse(Long courseId, boolean isPaid) {
        var course = courseRepository.findById(courseId)
                .orElseThrow(() -> new NotFoundException("Course Not Found"));
        userCourseRepository.save(new UserCourse(null,
                userService.getCurrentUser(),
                course,
                UserCourseLessonStatusEnum.STARTED.getStatus(), isPaid));

        startLesson(course.getLessons()
                .stream()
                .sorted(Comparator.comparing(Lesson::getLessonNumber))
                .limit(1)
                .toList().getFirst().getId());

        return findByIdAndProgress(courseId);
    }


    @Override
    public List<LessonDto> doneLesson(Long lessonId) {
        var lesson = lessonRepository.findById(lessonId).orElseThrow(() -> new NotFoundException("Lesson Not Found"));
        var courseAndUser = getByCourseAndUser(lesson.getCourse());
        UserCourseLesson userCourseLesson = userCourseLessonRepository.findByLessonIdAndUserCourse(lessonId, courseAndUser)
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
        var courseAndUser = getByCourseAndUser(lesson.getCourse());

        UserCourseLesson alreadyExists = userCourseLessonRepository.findByLessonIdAndUserCourse(lessonId, courseAndUser)
                .orElse(null);
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
        return getByCourseAndUser(lesson.getCourse());
    }

    @Override
    public List<Tags> findAllTags() {
        return tagsRepository.findAll();
    }

    @Override
    public CourseDetailsDto findByIdWithoutProgress(Long id) {
        return courseRepository.findById(id)
                .map(course ->
                        new CourseDetailsDto(
                                course.getId(),
                                course.getTitle(),
                                course.getDescription(),
                                course.getBase64Images().getBase64Image(),
                                course.getTags().stream().map(Tags::getId).collect(Collectors.toSet()),
                                course.getCategories().stream().map(Category::getId).collect(Collectors.toSet()),
                                course.getLessons().stream()
                                        .map(lesson -> new LessonDto(
                                                lesson.getId(), lesson.getLessonNumber(), lesson.getTitle(), lesson.getVideoUrl(),
                                                lesson.getBodyText(), lesson.getStatus(), lesson.getIsCompleted())
                                        ).collect(Collectors.toList()),
                                getDefaultTestDtoForNonProgresses(course),
                                course.getStatus(),
                                course.getDemoUrl(), false
                        )
                )
                .orElseThrow(() -> new NoSuchElementException(COURSE_NOT_FOUND));
    }

    private String getCurrentCourseStatus(Course course) {
        return getByCourseAndUser(course).getStatus();
    }

    @Override
    public CourseDetailsDto findByIdAndProgress(Long courseId) {
        var isNotExistCourseAndUser = getByCourseAndUser(courseRepository.findById(courseId)
                .orElseThrow(() -> new NoSuchElementException(COURSE_NOT_FOUND))) == null;
        if (isNotExistCourseAndUser) {
            return findByIdWithoutProgress(courseId);
        }
        return courseRepository.findById(courseId)
                .map(course -> {
                            var courseAndUser = getByCourseAndUser(course);
                            var userCourseLessons = getUserCourseLessons(courseAndUser);
                            return new CourseDetailsDto(
                                    course.getId(),
                                    course.getTitle(),
                                    course.getDescription(),
                                    course.getBase64Images().getBase64Image(),
                                    course.getTags().stream().map(Tags::getId).collect(Collectors.toSet()),
                                    course.getCategories().stream().map(Category::getId).collect(Collectors.toSet()),
                                    getCurrentLessonsList(userCourseLessons),
                                    getCurrentTest(userCourseLessons),
                                    getCurrentCourseStatus(course),
                                    course.getDemoUrl(),
                                    courseAndUser.getIsPaid()
                            );

                        }
                )
                .orElseThrow(() -> new NoSuchElementException(COURSE_NOT_FOUND));

    }

    public TestDto getCurrentTestByCourse(Long courseId) {
        var course = courseRepository.findById(courseId)
                .orElseThrow(() -> new NoSuchElementException(COURSE_NOT_FOUND));
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")) {
            return getDefaultTestDtoForNonProgresses(course);
        }
        var courseAndUser = getByCourseAndUser(course);
        if (courseAndUser == null) {
            return getDefaultTestDtoForNonProgresses(course);
        }
        List<UserCourseLesson> userCourseLessons = getUserCourseLessons(courseAndUser);
        return getCurrentTest(userCourseLessons);
    }

    private TestDto getDefaultTestDtoForNonProgresses(Course course) {
        return testRepository.findById(course.getTest().getId())
                .map(test -> new TestDto(
                                test.getId(),
                                test.getTitle(),
                                UserTestStatusEnum.NOT_ACTIVE.getStatus(),
                                test.getType(),
                                course.getId(), null, null, null
                        )
                ).orElse(null);
    }

    private List<UserCourseLesson> getUserCourseLessons(UserCourse courseAndUser) {
        return userCourseLessonRepository.findByUserCourseId(courseAndUser.getId());
    }

    private UserCourse getByCourseAndUser(Course course) {
        return userCourseRepository.findByCourseAndUser(course, userService.getCurrentUser());
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
                                first.getUserCourse().getCourse().getId(), null, null, null
                        )
                )
                .orElse(null);
    }

    private List<LessonDto> getCurrentLessonsList(List<UserCourseLesson> userCourseLesson) {
        List<Lesson> lessonsWithActiveState = userCourseLesson.getFirst().getUserCourse().getCourse().getLessons().stream().toList();
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

    @Override
    public void payment(Long id) {
        var courseAndUser = getByCourseAndUser(courseRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(COURSE_NOT_FOUND)));
        if (null == courseAndUser) {
            startCourse(id, true);
            return;
        }
        courseAndUser.setIsPaid(true);
        userCourseRepository.save(courseAndUser);
    }
}

package com.rdlab.education.utils.mapper;

public class CourseMapper {
    public static CourseDTO toDTO(Course course) {
        CourseDTO dto = new CourseDTO();
        dto.setId(course.getId());
        dto.setTitle(course.getTitle());
        dto.setDescription(course.getDescription());
        dto.setStatus(course.getStatus());
        dto.setLessons(course.getLessons().stream().map(CourseMapper::toLessonDTO).toList());
        dto.setTest(toTestDTO(course.getTest()));
        return dto;
    }

    public static Course toEntity(CourseDTO courseDTO) {
        Course course = new Course();
        course.setTitle(courseDTO.getTitle());
        course.setDescription(courseDTO.getDescription());
        course.setStatus(courseDTO.getStatus());
        course.setLessons(courseDTO.getLessons().stream().map(CourseMapper::toLessonEntity).toList());
        course.setTest(toTestEntity(courseDTO.getTest()));
        return course;
    }

    public static void updateEntity(Course course, CourseDTO courseDTO) {
        course.setTitle(courseDTO.getTitle());
        course.setDescription(courseDTO.getDescription());
        course.setStatus(courseDTO.getStatus());
    }

    private static LessonDTO toLessonDTO(Lesson lesson) {
        return new LessonDTO(lesson.getId(), lesson.getTitle(), lesson.getContent());
    }

    private static Lesson toLessonEntity(LessonDTO lessonDTO) {
        return new Lesson(lessonDTO.getId(), lessonDTO.getTitle(), lessonDTO.getContent());
    }

    private static TestDTO toTestDTO(Test test) {
        return new TestDTO(test.getId(), test.getTitle(), test.getState(),
                test.getQuestions().stream().map(CourseMapper::toQuestionDTO).toList());
    }

    private static Test toTestEntity(TestDTO testDTO) {
        Test test = new Test();
        test.setTitle(testDTO.getTitle());
        test.setState(testDTO.getState());
        test.setQuestions(testDTO.getQuestions().stream().map(CourseMapper::toQuestionEntity).toList());
        return test;
    }

    private static QuestionDTO toQuestionDTO(Question question) {
        return new QuestionDTO(question.getId(), question.getText(),
                question.getAnswers().stream().map(CourseMapper::toAnswerDTO).toList());
    }

    private static Question toQuestionEntity(QuestionDTO questionDTO) {
        Question question = new Question();
        question.setText(questionDTO.getText());
        question.setAnswers(questionDTO.getAnswers().stream().map(CourseMapper::toAnswerEntity).toList());
        return question;
    }

    private static AnswerDTO toAnswerDTO(Answer answer) {
        return new AnswerDTO(answer.getId(), answer.getText(), answer.getCorrect());
    }

    private static Answer toAnswerEntity(AnswerDTO answerDTO) {
        return new Answer(answerDTO.getId(), answerDTO.getText(), answerDTO.getCorrect());
    }
}

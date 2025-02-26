package com.rdlab.education.domain.repository.edu;

import com.rdlab.education.domain.entity.auth.Users;
import com.rdlab.education.domain.entity.edu.Course;
import com.rdlab.education.domain.entity.edu.UserCourse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCourseRepository extends JpaRepository<UserCourse, Long> {

    UserCourse findByCourseAndUser(Course course, Users user);

}

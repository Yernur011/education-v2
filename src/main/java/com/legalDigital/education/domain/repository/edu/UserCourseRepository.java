package com.legalDigital.education.domain.repository.edu;

import com.legalDigital.education.domain.entity.auth.Users;
import com.legalDigital.education.domain.entity.edu.Course;
import com.legalDigital.education.domain.entity.edu.UserCourse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCourseRepository extends JpaRepository<UserCourse, Long> {

    UserCourse findByCourseAndUser(Course course, Users user);

    Page<UserCourse> findByUserAndStatus(Users user, String status, Pageable pageable);

}

package com.legalDigital.education.domain.repository.edu;

import com.legalDigital.education.domain.entity.edu.UserTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTestRepository extends JpaRepository<UserTest, Long> {
    Page<UserTest> findAllByUserId(Long userId, Pageable pageable);
}

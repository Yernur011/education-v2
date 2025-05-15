package com.legalDigital.education.domain.repository;

import com.legalDigital.education.domain.entity.edu.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {
}

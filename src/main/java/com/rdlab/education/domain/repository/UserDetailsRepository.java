package com.rdlab.education.domain.repository;

import com.rdlab.education.domain.entity.edu.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {
}

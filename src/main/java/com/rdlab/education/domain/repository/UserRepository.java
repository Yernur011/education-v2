package com.rdlab.education.domain.repository;

import com.rdlab.education.domain.entity.auth.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByUsername(String username);

    boolean existsByUsername(String username);

    @Query(value = """
            SELECT * FROM users
            WHERE category_id_list @> to_jsonb(array[:id])::jsonb
            """, nativeQuery = true)
    List<Users> findByCategoryIdContains(@Param("id") Long id);
}

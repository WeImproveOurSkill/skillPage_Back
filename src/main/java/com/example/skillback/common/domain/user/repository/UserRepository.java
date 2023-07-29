package com.example.skillback.common.domain.user.repository;

import com.example.skillback.common.domain.user.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

    boolean existsByUserIdentifier(String userIdentifier);

    Optional<User> findByUserIdentifier(String userIdentifier);
}

package com.example.skillback.common.domain.user.repository;

import com.example.skillback.common.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

}

package com.example.skillback.common.domain.user.repository;

import com.example.skillback.common.domain.user.dto.CheckUser;
import java.util.Optional;

public interface UserRepositoryQuery {

    Optional<CheckUser> getCheckUserByUserIdentifier(String identifier);
}

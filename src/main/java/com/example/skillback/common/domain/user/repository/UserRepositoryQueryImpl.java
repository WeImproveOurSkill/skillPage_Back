package com.example.skillback.common.domain.user.repository;

import static com.example.skillback.common.domain.user.entity.QUser.user;

import com.example.skillback.common.domain.user.dto.CheckUser;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserRepositoryQueryImpl implements UserRepositoryQuery {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Optional<CheckUser> getCheckUserByUserIdentifier(String identifier) {
        return Optional.ofNullable(jpaQueryFactory.select(Projections.constructor(CheckUser.class,
            user.userIdentifier,
            user.password,
            user.rollEnum,
            user.userName))
            .from(user)
            .where(user.userIdentifier.eq(identifier))
            .fetchOne());
    }
}

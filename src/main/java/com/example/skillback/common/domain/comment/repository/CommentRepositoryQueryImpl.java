package com.example.skillback.common.domain.comment.repository;

import static com.example.skillback.common.domain.comment.entity.QComment.comment;

import com.example.skillback.common.domain.comment.dto.CommentResponse;
import com.example.skillback.common.domain.comment.entity.QComment;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CommentRepositoryQueryImpl implements CommentRepositoryQuery{

    private final JPAQueryFactory jpaQueryFactory;


    @Override
    public Optional<CommentResponse> findByCommentId(Long commentId) {
        return Optional.ofNullable(jpaQueryFactory.select(Projections.constructor(CommentResponse.class,
            comment.user.userIdentifier,
            comment.commentContent,
            comment.commentLike))
            .from(comment)
            .where(comment.id.eq(commentId))
            .fetchOne());
    }
}

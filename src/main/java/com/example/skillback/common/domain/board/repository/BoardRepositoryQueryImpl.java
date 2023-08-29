package com.example.skillback.common.domain.board.repository;

import static com.example.skillback.common.domain.board.entity.QBoard.board;
import static com.example.skillback.common.domain.product.entity.QProduct.product;

import com.example.skillback.common.domain.board.dto.BoardListResponse;
import com.example.skillback.common.domain.board.dto.BoardResponse;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
public class BoardRepositoryQueryImpl implements  BoardRepositoryQuery{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<BoardListResponse> findAllBoardPage(Pageable pageable) {
        List<BoardListResponse> boardListResponseList =jpaQueryFactory
            .select(Projections.constructor(BoardListResponse.class,
                board.id,
                board.title,
                board.user.userIdentifier))
            .from(board)
            .limit(pageable.getPageSize())
            .offset(pageable.getOffset())
            .fetch();

        Long totalSize = getTotalSize().fetch().get(0);

        return PageableExecutionUtils.getPage(boardListResponseList, pageable, () -> totalSize);
    }

    @Override
    public Optional<BoardResponse> findByBoardId(Long boardId) {
        return Optional.of(jpaQueryFactory.select(Projections.constructor(BoardResponse.class,
                board.id,
                board.title,
                board.content,
                board.user.userIdentifier,
                board.commentList))
            .from(board)
            .where(board.id.eq(boardId))
            .fetchOne());
    }

    private JPAQuery<Long> getTotalSize() {
        return jpaQueryFactory.select(Wildcard.count).from(product);
    }
}

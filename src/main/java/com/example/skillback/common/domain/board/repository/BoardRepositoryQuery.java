package com.example.skillback.common.domain.board.repository;

import com.example.skillback.common.domain.board.dto.BoardListResponse;
import com.example.skillback.common.domain.board.dto.BoardResponse;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardRepositoryQuery {

    Page<BoardListResponse> findAllBoardPage(Pageable pageable);

    Optional<BoardResponse> findByBoardId(Long BoardId);

}

package com.example.skillback.common.domain.board.service;

import com.example.skillback.common.domain.board.dto.BoardListResponse;
import com.example.skillback.common.domain.board.dto.BoardRequest;
import com.example.skillback.common.domain.board.dto.BoardResponse;
import com.example.skillback.common.domain.user.entity.User;
import com.example.skillback.common.dtos.PageDto;
import java.util.List;
import org.springframework.data.domain.Page;

public interface BoardService {

    void createBoeard(BoardRequest boardRequest, User user);

    Page<BoardListResponse> getBoardList(PageDto pageDto);

    BoardResponse getBoard(Long boardId);

    void recommendBoard(Long boardId);

    void updateBoard(Long boardId, BoardRequest boardRequest, User user);

    void deleteBoard(Long boardId, User user);
}

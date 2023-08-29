package com.example.skillback.common.domain.board.service;

import com.example.skillback.common.domain.board.dto.BoardListResponse;
import com.example.skillback.common.domain.board.dto.BoardRequest;
import com.example.skillback.common.domain.board.dto.BoardResponse;
import com.example.skillback.common.domain.board.entity.Board;
import com.example.skillback.common.domain.board.repository.BoardRepository;
import com.example.skillback.common.domain.comment.dto.CommentResponse;
import com.example.skillback.common.domain.user.entity.User;
import com.example.skillback.common.dtos.PageDto;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {


    private final BoardRepository boardRepository;

    @Override
    @Transactional
    public void createBoeard(BoardRequest boardRequest, User user) {
        Board board = Board.builder()
            .boardCategotyEnum(boardRequest.getBoardCategotyEnum())
            .title(boardRequest.getTitle())
            .content(boardRequest.getContent())
            .user(user)
            .build();
        boardRepository.save(board);
    }

    @Override
    @Transactional

    public Page<BoardListResponse> getBoardList(PageDto pageDto) {
        Page<BoardListResponse> boardList = boardRepository.findAllBoardPage(pageDto.toPageable());
        return boardList;
    }

    @Override
    @Transactional
    public BoardResponse getBoard(Long boardId) {
        return getBoardResponse(boardId);
    }

    @Override
    @Transactional
    public void recommendBoard(Long boardId) {
        Board board = getBoardById(boardId);
        board.plusLike();
    }

    @Override
    public void updateBoard(Long boardId, BoardRequest boardRequest, User user) {
        Board board = getBoardById(boardId);
        if (!user.equals(board.getUser())) {
            throw new IllegalArgumentException("해당 유저는 접근권한이 없습니다");
        }
        board.update(boardRequest);
    }

    @Override
    public void deleteBoard(Long boardId, User user) {
        Board board = getBoardById(boardId);
        if (!user.equals(board.getUser())) {
            throw new IllegalArgumentException("해당 유저는 권한이 없습니다");
        }
        boardRepository.delete(board);
    }

    private Board getBoardById(Long boardId) {
        return boardRepository.findById(boardId)
            .orElseThrow(() -> new IllegalArgumentException("해당 게시글은 존재하지않습니다"));
    }

    private BoardResponse getBoardResponse(Long boardId) {
        return boardRepository.findByBoardId(boardId)
            .orElseThrow(() -> new IllegalArgumentException("해당 게시글은 존재하지않습니다"));
    }
}

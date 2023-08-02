package com.example.skillback.common.domain.board.controller;


import static com.example.skillback.common.domain.board.controller.BoardController.BOARD_API_URI;
import static com.example.skillback.common.utils.HttpResponseEntity.RESPONSE_CREATED;
import static com.example.skillback.common.utils.HttpResponseEntity.RESPONSE_DELETED;
import static com.example.skillback.common.utils.HttpResponseEntity.RESPONSE_OK;
import static com.example.skillback.common.utils.HttpResponseEntity.RESPONSE_UPDATE;

import com.example.skillback.common.domain.board.dto.BoardResponse;
import com.example.skillback.common.domain.board.dto.BoardListResponse;
import com.example.skillback.common.domain.board.dto.BoardRequest;
import com.example.skillback.common.domain.board.service.BoardService;
import com.example.skillback.common.dtos.StatusResponse;
import com.example.skillback.common.security.UserDetailsImpl;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(BOARD_API_URI)
public class BoardController {

    public static final String BOARD_API_URI = "/board";
    private final BoardService boardService;

    @PostMapping("/cb")
    public ResponseEntity<StatusResponse> createBoard(@RequestBody BoardRequest boardRequest, @AuthenticationPrincipal
        UserDetailsImpl userDetails) {
        boardService.createBoeard(boardRequest, userDetails.getUser());
        return RESPONSE_CREATED;
    }

    @GetMapping("/gb")
    public ResponseEntity<List<BoardListResponse>> getBoardList() {
        List<BoardListResponse> boardListResponseList = boardService.getBoardList();
        return ResponseEntity.ok().body(boardListResponseList);
    }

    @GetMapping("/gb/{boardId}")
    public ResponseEntity<BoardResponse> getBoard(@PathVariable Long boardId) {
        BoardResponse boardResponse = boardService.getBoard(boardId);
        return ResponseEntity.ok().body(boardResponse);
    }

    @PostMapping("/rb/{boardId}")
    public ResponseEntity<StatusResponse> recommendBoard(@PathVariable Long boardId) {
        boardService.recommendBoard(boardId);
        return RESPONSE_OK;
    }

    @PatchMapping("/pb/{boardId}")
    public ResponseEntity<StatusResponse> updateBoard(@PathVariable Long boardId, @RequestBody BoardRequest boardRequest,@AuthenticationPrincipal UserDetailsImpl userDetails) {
        boardService.updateBoard(boardId, boardRequest, userDetails.getUser());
        return RESPONSE_UPDATE;
    }

    @DeleteMapping("/db/{boardId}")
    public ResponseEntity<StatusResponse> deleteBoard(@PathVariable Long boardId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        boardService.deleteBoard(boardId, userDetails.getUser());
        return RESPONSE_DELETED;
    }
}

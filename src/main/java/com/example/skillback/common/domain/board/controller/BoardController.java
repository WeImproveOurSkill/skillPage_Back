package com.example.skillback.common.domain.board.controller;


import static com.example.skillback.common.domain.board.controller.BoardController.BOARD_API_URI;

import com.example.skillback.common.domain.board.dto.BoardRequest;
import com.example.skillback.common.domain.board.service.BoardService;
import lombok.RequiredArgsConstructor;
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
//    private final BoardService boardService;

    @PostMapping("/cb")
    public void createBoard(@RequestBody BoardRequest boardRequest) {
        System.out.println("게시글 생성 연결 API");
        System.out.println(boardRequest);
    }

    @GetMapping("/gb")
    public void getBoardList() {
        System.out.println("게시글 리스트 조회 연결 API");
    }

    @GetMapping("/gb/{boardId}")
    public void getBoard(@PathVariable Long boardId) {
        System.out.println("게시글 조회 연결 API");
    }

    @PostMapping("/rb/{boardId}")
    public void recommendBoard(@PathVariable Long boardId) {
        System.out.println("게시글 추천 연결 API");
    }

    @PatchMapping("/pb/{boardId}")
    public void updateBoard(@PathVariable Long boardId, @RequestBody BoardRequest boardRequest) {
        System.out.println("게시글 수정 연결 API");
        System.out.println(boardId);
        System.out.println(boardRequest);
    }

    @DeleteMapping("/db/{boardId}")
    public void deleteBoard(@PathVariable Long boardId) {
        System.out.println("게시글 삭제 연결 API");

    }
}

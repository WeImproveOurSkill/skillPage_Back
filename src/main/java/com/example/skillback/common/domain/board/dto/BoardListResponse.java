package com.example.skillback.common.domain.board.dto;

import com.example.skillback.common.domain.board.entity.Board;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BoardListResponse {

    private Long boardId;
    private String title;
    private String userName;

    public BoardListResponse(Board board) {
        this.boardId = board.getId();
        this.userName = board.getUser().getUserName();
        this.title = board.getTitle();
    }
}

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
public class BoardResponse {
    private Long boardId;
    private String title;
    private String content;
    private String userName;

    public BoardResponse(Board board) {
        this.boardId = board.getId();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.userName = board.getUser().getUserName();
    }
}

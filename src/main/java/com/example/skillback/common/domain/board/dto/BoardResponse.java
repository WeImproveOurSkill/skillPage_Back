package com.example.skillback.common.domain.board.dto;

import com.example.skillback.common.domain.board.entity.Board;
import com.example.skillback.common.domain.comment.dto.CommentResponse;
import com.example.skillback.common.domain.comment.entity.Comment;
import java.util.List;
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
    private List<CommentResponse> comments;

//    public BoardResponse(Board board) {
//        this.boardId = board.getId();
//        this.title = board.getTitle();
//        this.content = board.getContent();
//        this.userName = board.getUser().getUserName();
//    }
}

package com.example.skillback.common.domain.comment.dto;

import com.example.skillback.common.domain.comment.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CommentResponse {

    private String userIdentifier;
    private String commentsContent;
    private Long commentLike;

    public CommentResponse(Comment comment) {
        this.userIdentifier = comment.getUser().getUserIdentifier();
        this.commentsContent = comment.getCommentContent();
        this.commentLike = comment.getCommentLike();
    }
}

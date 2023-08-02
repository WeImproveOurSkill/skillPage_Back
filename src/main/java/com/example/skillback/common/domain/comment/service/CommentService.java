package com.example.skillback.common.domain.comment.service;

import com.example.skillback.common.domain.comment.dto.CommentRequest;
import com.example.skillback.common.domain.user.entity.User;

public interface CommentService {

    void createComment(Long boardId, CommentRequest commentRequest, User user);

    void recommendComment(Long commentId);

    void updateComment(Long commentId, CommentRequest commentRequest, User user);

    void deleteComment(Long commentId, User user);
}

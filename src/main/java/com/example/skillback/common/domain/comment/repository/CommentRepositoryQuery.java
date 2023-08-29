package com.example.skillback.common.domain.comment.repository;

import com.example.skillback.common.domain.comment.dto.CommentResponse;
import java.util.Optional;

public interface CommentRepositoryQuery {

    Optional<CommentResponse> findByCommentId(Long commentId);
}

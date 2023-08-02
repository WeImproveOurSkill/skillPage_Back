package com.example.skillback.common.domain.comment.controller;

import static com.example.skillback.common.domain.comment.controller.CommentController.COMMENT_API_URI;
import static com.example.skillback.common.utils.HttpResponseEntity.RESPONSE_CREATED;
import static com.example.skillback.common.utils.HttpResponseEntity.RESPONSE_DELETED;
import static com.example.skillback.common.utils.HttpResponseEntity.RESPONSE_UPDATE;

import com.example.skillback.common.domain.comment.dto.CommentRequest;
import com.example.skillback.common.domain.comment.service.CommentService;
import com.example.skillback.common.domain.user.entity.User;
import com.example.skillback.common.dtos.StatusResponse;
import com.example.skillback.common.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(COMMENT_API_URI)
public class CommentController {

    public static final String COMMENT_API_URI = "/commnet";
    private final CommentService commentService;

    @PostMapping("/cc/board/{boardId}")
    public ResponseEntity<StatusResponse> createComment(@PathVariable Long boardId,
        @RequestBody CommentRequest commentRequest,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        commentService.createComment(boardId, commentRequest, userDetails.getUser());
        return RESPONSE_CREATED;
    }

    @PostMapping("/rc/{commentId}")
    public ResponseEntity<StatusResponse> recommendComment(@PathVariable Long commentId) {
        commentService.recommendComment(commentId);
        return RESPONSE_UPDATE;
    }

    @PatchMapping("/pc/{commentId}")
    public ResponseEntity<StatusResponse> updateComment(@PathVariable Long commentId,
        @RequestBody CommentRequest commentRequest,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        commentService.updateComment(commentId, commentRequest, userDetails.getUser());
        return RESPONSE_UPDATE;
    }

    @DeleteMapping("/dc/{commentId}")
    public ResponseEntity<StatusResponse> deleteComment(@PathVariable Long commentId,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        commentService.deleteComment(commentId, userDetails.getUser());
        return RESPONSE_DELETED;
    }
}

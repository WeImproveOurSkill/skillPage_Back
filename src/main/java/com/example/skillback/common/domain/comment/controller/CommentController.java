package com.example.skillback.common.domain.comment.controller;

import static com.example.skillback.common.domain.comment.controller.CommentController.COMMENT_API_URI;

import com.example.skillback.common.domain.comment.dto.CommentRequest;
import com.example.skillback.common.domain.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
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
//    private final CommentService commentService;

    @PostMapping("/cc/board/{boardId}")
    public void createComment(@PathVariable Long boardId,
        @RequestBody CommentRequest commentRequest) {
        System.out.println("댓글 생성 API");
        System.out.println(boardId);
        System.out.println(commentRequest);
    }

    @PostMapping("/rc/{commentId}")
    public void recommendComment(@PathVariable Long commentId) {
        System.out.println("댓글 추천 API");
    }

    @PatchMapping("/pc/{commentId}")
    public void updateComment(@PathVariable Long commentId,
        @RequestBody CommentRequest commentRequest) {
        System.out.println("댓글 수정 API");
        System.out.println(commentId);
        System.out.println(commentRequest);
    }

    @DeleteMapping("/dc/{commentId}")
    public void deleteComment(@PathVariable Long commentId) {
        System.out.println("댓글 삭제 API");
    }
}

package com.example.skillback.common.domain.comment.service;

import com.example.skillback.common.domain.board.entity.Board;
import com.example.skillback.common.domain.board.repository.BoardRepository;
import com.example.skillback.common.domain.comment.dto.CommentRequest;
import com.example.skillback.common.domain.comment.entity.Comment;
import com.example.skillback.common.domain.comment.repository.CommentRepository;
import com.example.skillback.common.domain.user.entity.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;

    @Override
    @Transactional
    public void createComment(Long boardId, CommentRequest commentRequest, User user) {
        Board board = getBoardById(boardId);
        Comment comment = Comment.builder()
            .commentContent(commentRequest.getCommentContent())
            .user(user)
            .board(board)
            .build();
        commentRepository.save(comment);
    }

    @Override
    @Transactional
    public void recommendComment(Long commentId) {
        Comment comment = getCommentById(commentId);
        comment.plusLike();
    }

    @Override
    @Transactional
    public void updateComment(Long commentId, CommentRequest commentRequest, User user) {
        Comment comment = getCommentById(commentId);
        if (!user.equals(comment.getUser())) {
            throw new IllegalArgumentException("해당 유저는 권한이 존재하지않습니다");
        }
        comment.update(commentRequest);
    }

    @Override
    @Transactional
    public void deleteComment(Long commentId, User user) {
        Comment comment = getCommentById(commentId);
        if (!user.equals(comment.getUser())) {
            throw new IllegalArgumentException("해당 유저는 권한이 존재하지않습니다");
        }
        commentRepository.delete(comment);
    }

    private Board getBoardById(Long boardId) {
        return boardRepository.findById(boardId)
            .orElseThrow(() -> new IllegalArgumentException("해당 게시글은 존재하지않습니다"));
    }

    private Comment getCommentById(Long commentId) {
        return commentRepository.findById(commentId)
            .orElseThrow(() -> new IllegalArgumentException("해당 댓글은 존재하지않습니다"));
    }
}

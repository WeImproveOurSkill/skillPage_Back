package com.example.skillback.common.domain.comment.repository;

import com.example.skillback.common.domain.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long>, CommentRepositoryQuery {

}

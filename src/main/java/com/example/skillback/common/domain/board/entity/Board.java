package com.example.skillback.common.domain.board.entity;

import com.example.skillback.common.TimeStamped;
import com.example.skillback.common.domain.comment.entity.Comment;
import com.example.skillback.common.domain.user.entity.User;
import com.example.skillback.common.enums.BoardCategotyEnum;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter

@Entity
@Table(name = "boards")
public class Board extends TimeStamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    @Column(name = "board_like")
    private Long boardLike =  0L;

    @Column(name = "board_content")
    private String content;

    @Column(name = "board_category")
    @Enumerated(EnumType.STRING)
    private BoardCategotyEnum boardCategotyEnum;

    @ManyToOne
    @JoinColumn(name = "writer_id")
    private User user;


    @OneToMany(mappedBy = "boards", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Comment> commentList = new ArrayList<>();


}

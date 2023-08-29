package com.example.skillback.common.domain.user.entity;

import com.example.skillback.common.domain.answer.entity.Answer;
import com.example.skillback.common.domain.board.entity.Board;
import com.example.skillback.common.domain.comment.entity.Comment;
import com.example.skillback.common.domain.s3.entity.FileForm;
import com.example.skillback.common.domain.product.entity.Product;
import com.example.skillback.common.domain.question.entity.Question;
import com.example.skillback.common.domain.review.entity.Review;
import com.example.skillback.common.enums.ActiveEnum;
import com.example.skillback.common.enums.RollEnum;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "user_identifier")
    private String userIdentifier;

    @Column(name = "email")
    private String email;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "password")
    private String password;

    @Column(name = "activated")
    @Enumerated(EnumType.STRING)
    private ActiveEnum activeEnum;

    @Column(name = "roll_enum")
    @Enumerated(EnumType.STRING)
    private RollEnum rollEnum;

    @Column(name = "recent_time")
    private LocalDateTime recentLogin;

    @Builder.Default
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Product> sellProduct = new LinkedHashSet<>();

    @Builder.Default
    @OneToMany(mappedBy = "user")
    private List<Board> boardList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "user")
    private List<Comment> commentList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "user")
    private List<Question> questionList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "user")
    private List<Answer> answers = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "user")
    private List<Review> reviewList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FileForm> fileFormList = new ArrayList<>();

    public void changePassword(String password) {
        this.password = password;
    }

    public void recentLogin(LocalDateTime localDateTime) {
        this.recentLogin = localDateTime;
    }
}

package com.example.skillback.common.domain.s3.entity;

import com.example.skillback.common.domain.review.entity.Review;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Table(name = "file_pic")
public class FilePic {

    @Id
    @Column(name = "file_id", nullable = false)
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "file_id")
    private FileForm fileForm;

    @ManyToOne
    @JoinColumn(name = "review_id")
    private Review review;
}

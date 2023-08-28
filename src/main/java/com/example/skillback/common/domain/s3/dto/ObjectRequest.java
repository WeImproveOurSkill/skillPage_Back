package com.example.skillback.common.domain.s3.dto;

import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ObjectRequest {
    private String bucketName;
    private String folderName;
    private ObjectMetadata objectMetadata;

}

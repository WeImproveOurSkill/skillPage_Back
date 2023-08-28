package com.example.skillback.common.domain.s3.service;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import org.springframework.web.multipart.MultipartFile;

public interface S3Service {

    void uploadPic(MultipartFile multipartFile, String bucketName) throws IOException;

    void createBucket(String bucketName);

//    Optional<File> convert(MultipartFile multipartFile);
}

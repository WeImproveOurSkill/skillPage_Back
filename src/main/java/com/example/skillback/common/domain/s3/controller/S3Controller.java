package com.example.skillback.common.domain.s3.controller;

import static com.example.skillback.common.domain.s3.controller.S3Controller.IMAGE_API_URI;
import static com.example.skillback.common.utils.HttpResponseEntity.RESPONSE_CREATED;
import static com.example.skillback.common.utils.HttpResponseEntity.RESPONSE_OK;

import com.example.skillback.common.domain.s3.service.S3Service;
import com.example.skillback.common.dtos.StatusResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping(IMAGE_API_URI)
public class S3Controller {

    public static final String IMAGE_API_URI = "/image";

    private final S3Service s3Service;

    @PostMapping
    public ResponseEntity<StatusResponse> createBucket(
        @RequestParam("bucketName") String bucketName) {
        s3Service.createBucket(bucketName);
        return RESPONSE_CREATED;
    }


    @PostMapping("/upload")
    public ResponseEntity<String> uploadPic(@RequestParam("bucketName") String bucketName,
        @RequestParam("pic") MultipartFile multipartFile) throws IOException {
        String url = s3Service.uploadPic(multipartFile, bucketName);
        return ResponseEntity.ok().body(url);
    }

}

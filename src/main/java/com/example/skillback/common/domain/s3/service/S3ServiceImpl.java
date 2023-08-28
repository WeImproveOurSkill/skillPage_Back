package com.example.skillback.common.domain.s3.service;


import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Slf4j
public class S3ServiceImpl implements S3Service {

    private final AmazonS3 s3;

    @Override
    public void createBucket(String bucketName) {
        try {
            // create bucket if the bucket name does not exist
            if (s3.doesBucketExistV2(bucketName)) {
                log.info("Bucket %s already exists.\n", bucketName);
            } else {
                s3.createBucket(bucketName);
                log.info("Bucket %s has been created.\n", bucketName);
            }
        } catch (AmazonS3Exception e) {
            e.printStackTrace();
        } catch (SdkClientException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void uploadPic(MultipartFile multipartFile, String bucketName) throws IOException {

        File uploadFile = convert(multipartFile).orElseThrow(
            () -> new IllegalArgumentException("파일 변환에 실패했습니다")
        );
        // create folder
        createFolder(bucketName);

       // upload local file
        uploadFile(bucketName, uploadFile);
    }

    private void uploadFile(String bucketName, File uploadFile) {
        String objectName = uploadFile.getName();
        try {
            s3.putObject(bucketName, objectName, uploadFile);
            log.info("Object %s has been created.\n", objectName);
            removeOriginFile(uploadFile);
        } catch (AmazonS3Exception e) {
            e.printStackTrace();
        } catch (SdkClientException e) {
            e.printStackTrace();
        }
    }

    private void createFolder(String bucketName) {
        String folderName = "sample-folder/";

        ObjectMetadata objectMetadata = getObjectMetadata();
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, folderName,
            new ByteArrayInputStream(new byte[0]), objectMetadata);

        try {
            s3.putObject(putObjectRequest);
            System.out.format("Folder %s has been created.\n", folderName);
        } catch (AmazonS3Exception e) {
            e.printStackTrace();
        } catch (SdkClientException e) {
            e.printStackTrace();
        }
    }

    private static ObjectMetadata getObjectMetadata() {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(0L);
        objectMetadata.setContentType("application/x-directory");
        return objectMetadata;
    }

    private static void removeOriginFile(File uploadFile) {
        if (uploadFile.delete()) {
            log.info(uploadFile.getName() + " 제거되었습니다");
        } else {
            log.info(uploadFile.getName() + " 제거되지 않았습니다");
        }
    }


    private Optional<File> convert(MultipartFile multipartFile) throws IOException {
        if (multipartFile.isEmpty()) {
            return Optional.empty();
        }
        String originalFilename = multipartFile.getOriginalFilename();
        File file = new File(getPathname(originalFilename));
        multipartFile.transferTo(file);
        return Optional.of(file);
    }

    private static String getPathname(String originalFilename) {
        return UUID.randomUUID() + "." + originalFilename.substring(
            originalFilename.lastIndexOf(".") + 1);
    }
}

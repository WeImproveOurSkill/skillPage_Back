package com.example.skillback.common.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmazonS3Config {

    @Value("${naver-cloud.accessKey}")
    private String accessKey;

    @Value("${naver-cloud.secretKey}")
    private String secretKey;

    @Value("${naver-cloud.s3.end-point}")
    private String endPoint;

    @Value("{naver-cloud.s3.region-name}")
    private String regionName;

    @Bean
    public AmazonS3 amazonS3Client(){
        AmazonS3 s3 = AmazonS3ClientBuilder.standard()
            .withEndpointConfiguration(new EndpointConfiguration(endPoint, regionName))
            .withCredentials(
                new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey)))
            .build();
        return s3;
    }
}

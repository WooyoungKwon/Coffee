package com.date.coffee.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class S3Config {

    @Bean
    public S3Client s3Client() {
        return S3Client.builder()
                .region(Region.AP_NORTHEAST_2)
                // local(username/.aws/credentials 에 저장된 AWS 자격 증명 파일을 사용하여 인증 처리
                .credentialsProvider(ProfileCredentialsProvider.create())
                .build();
    }
}

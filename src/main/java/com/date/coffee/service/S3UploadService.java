package com.date.coffee.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.ObjectCannedACL;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class S3UploadService {
    private final S3Client s3Client;

//    private final AmazonS3Client s3Client;

    @Value("${s3.bucket}")
    private String bucket;

    public void upload(String path, File file) {
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucket)
                .key(path)
                .acl(ObjectCannedACL.BUCKET_OWNER_FULL_CONTROL)
                .build();

        s3Client.putObject(putObjectRequest, RequestBody.fromFile(file));
    }
    PutObjectRequest putObjectRequest = new PutObjectRequest(bucket);

//    public String upload(MultipartFile file) throws IOException {
//        // S3에 올라가는 파일의 이름 변경
//        String originalFilename = file.getOriginalFilename();
//        String fileName = changeFileName(originalFilename);
//
//        // S3에 올라가는 파일의 메타 데이터 생성
//        ObjectMetadata metadata = new ObjectMetadata();
//        metadata.setContentType(file.getContentType());
//        metadata.setContentLength(file.getSize());
//
//        // S3에 파일 업로드
//        s3Client.putObject(bucket, fileName, file.getInputStream(), metadata);
//
//        // 업로드한 파일 URL 리턴
//        return s3Client.getUrl(bucket, fileName).toString();
//    }

    private String changeFileName(String originalFilename) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        return originalFilename + "_" + LocalDateTime.now().format(formatter);
    }
}

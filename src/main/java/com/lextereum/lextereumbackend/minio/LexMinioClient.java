package com.lextereum.lextereumbackend.minio;

import com.lextereum.lextereumbackend.config.MinioProperties;
import io.minio.MinioClient;
import io.minio.errors.*;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.xmlpull.v1.XmlPullParserException;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

@Slf4j
@Component
@RequiredArgsConstructor
public class LexMinioClient {

    private final MinioClient minioClient;
    private final MinioProperties minioProperties;

    @PostConstruct
    public void init() throws IOException, InvalidKeyException, NoSuchAlgorithmException, InsufficientDataException, InvalidResponseException, InternalException, NoResponseException, InvalidBucketNameException, XmlPullParserException, ErrorResponseException, RegionConflictException {
        if (!minioClient.bucketExists(minioProperties.getBucket())) {
            log.debug("Creating new bucket {}", minioProperties.getBucket());
            minioClient.makeBucket(minioProperties.getBucket());
        }
    }

    @SneakyThrows
    public void uploadFile(MultipartFile file, String filename) {
        minioClient.putObject(
                minioProperties.getBucket(),
                filename,
                file.getInputStream(),
                file.getSize(),
                new HashMap<>(),
                null,
                file.getContentType()
        );
    }

    @SneakyThrows
    public InputStream getFileContent(String fileId) {
        return minioClient.getObject(minioProperties.getBucket(), fileId);
    }

    @SneakyThrows
    public void deleteFile(String fileId) {
        minioClient.removeObject(minioProperties.getBucket(), fileId);
    }
}

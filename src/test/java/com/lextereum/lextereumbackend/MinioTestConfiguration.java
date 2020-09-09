package com.lextereum.lextereumbackend;

import com.lextereum.lextereumbackend.minio.LexMinioClient;
import org.springframework.context.annotation.Configuration;

import static org.mockito.Mockito.mock;

@Configuration
public class MinioTestConfiguration {

    public LexMinioClient lexMinioClient() {
        return mock(LexMinioClient.class);
    }
}

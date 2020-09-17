package com.lextereum.lextereumbackend.service;

import com.google.common.hash.Hashing;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.io.InputStream;
@Service
public class ImageHashService {

    @SneakyThrows
    public String getImageHash(InputStream image) {
        return Hashing.sha256()
                      .hashBytes(image.readAllBytes())
                      .toString();
    }
}

package com.lextereum.lextereumbackend.utils;

import com.lextereum.lextereumbackend.exception.FailedToReadByteImageException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

public class ByteOperationUtils {

    public static BufferedImage createImageFromBytes(byte[] imageData) {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(imageData);
        try {
            return ImageIO.read(byteArrayInputStream);
        } catch (IOException e) {
            throw new FailedToReadByteImageException(e.getMessage());
        }
    }
}

package com.lextereum.lextereumbackend.config;

import net.sourceforge.tess4j.Tesseract;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TesseractConfiguration {

    @Value("${tess.data.path}")
    private String tessdataPath;
    @Value("${tess.data.language}")
    private String language;

    @Bean
    public Tesseract tesseract() {
        Tesseract tesseract = new Tesseract();
        tesseract.setLanguage(language);
        tesseract.setDatapath(tessdataPath);
        tesseract.setPageSegMode(1);
        tesseract.setOcrEngineMode(1);
        return tesseract;
    }
}

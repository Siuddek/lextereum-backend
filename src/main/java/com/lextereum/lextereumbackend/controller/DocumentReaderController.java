package com.lextereum.lextereumbackend.controller;

import com.lextereum.lextereumbackend.minio.LexMinioClient;
import com.lextereum.lextereumbackend.repositories.SellAgreementDto;
import com.lextereum.lextereumbackend.service.DocumentReaderService;
import lombok.RequiredArgsConstructor;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;


@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200") //TODO use docker
@RequestMapping("/documents")
public class DocumentReaderController {

    private final DocumentReaderService documentReader;
    private final LexMinioClient minioClient;

    @PutMapping("/read")
    @ResponseStatus(HttpStatus.CREATED)
    public SellAgreementDto readDocument(@RequestParam("documentImage") MultipartFile documentImage) throws TesseractException, IOException {
        SellAgreementDto sellAgreementDto = documentReader.readDocument(documentImage.getBytes());
        minioClient.uploadFile(documentImage, sellAgreementDto.getDocumentID());
        return sellAgreementDto;
    }

    @PutMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveDocumentDetails(@RequestParam("sellAgreement") SellAgreementDto sellAgreementDto) {
        InputStream documentImage = minioClient.getFileContent(sellAgreementDto.getDocumentID());
    }
}

package com.lextereum.lextereumbackend.controller;

import com.lextereum.lextereumbackend.email.EmailSender;
import com.lextereum.lextereumbackend.minio.LexMinioClient;
import com.lextereum.lextereumbackend.model.User;
import com.lextereum.lextereumbackend.model.SellAgreementDto;
import com.lextereum.lextereumbackend.repositories.UserRepository;
import com.lextereum.lextereumbackend.service.DocumentReaderService;
import lombok.RequiredArgsConstructor;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200") //TODO use docker
@RequestMapping("/documents")
public class DocumentController {

    private final DocumentReaderService documentReader;
    private final LexMinioClient minioClient;
    private final UserRepository userRepository;
    private final EmailSender emailSender;

    @PutMapping("/read")
    @ResponseStatus(HttpStatus.CREATED)
    public SellAgreementDto readDocument(@RequestParam("documentImage") MultipartFile documentImage) throws TesseractException, IOException {
        SellAgreementDto sellAgreementDto = documentReader.readDocument(documentImage.getBytes());
        minioClient.uploadFile(documentImage, sellAgreementDto.getDocumentID());
        return sellAgreementDto;
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public boolean saveDocumentDetails(@RequestBody SellAgreementDto sellAgreement) {
        // InputStream documentImage = minioClient.getFileContent(sellAgreementDto.getDocumentID());
        User validationUser = userRepository.findUserById(sellAgreement.getTargetID());
        emailSender.sendEmail(validationUser.getEmail(), "registry: " + sellAgreement.getMortgageRegister());
        return true;
    }
}

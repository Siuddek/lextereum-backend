package com.lextereum.lextereumbackend.controller;

import com.lextereum.lextereumbackend.email.EmailSender;
import com.lextereum.lextereumbackend.minio.LexMinioClient;
import com.lextereum.lextereumbackend.repositories.SellAgreement;
import com.lextereum.lextereumbackend.repositories.SellAgreementRepository;
import com.lextereum.lextereumbackend.repositories.User;
import com.lextereum.lextereumbackend.model.SellAgreementDto;
import com.lextereum.lextereumbackend.repositories.UserRepository;
import com.lextereum.lextereumbackend.service.DocumentReaderService;
import com.lextereum.lextereumbackend.service.ImageHashService;
import lombok.RequiredArgsConstructor;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;


@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/documents")
public class DocumentController {

    private final DocumentReaderService documentReader;
    private final LexMinioClient minioClient;
    private final UserRepository userRepository;
    private final EmailSender emailSender;
    private final SellAgreementRepository sellAgreementRepository;
    private final ImageHashService hashService;

    @PutMapping("/read")
    @ResponseStatus(HttpStatus.CREATED)
    public SellAgreementDto readDocument(@RequestParam("documentImage") MultipartFile documentImage) throws TesseractException, IOException {
        SellAgreementDto sellAgreementDto = documentReader.readDocument(documentImage.getBytes());
        minioClient.uploadFile(documentImage, sellAgreementDto.getDocumentID());
        return sellAgreementDto;
    }

    @PostMapping("/validate")
    @ResponseStatus(HttpStatus.CREATED)
    public String validateDocumentDetails(@RequestBody SellAgreementDto sellAgreement) {
        InputStream documentImage = minioClient.getFileContent(sellAgreement.getDocumentID());
        User validationUser = userRepository.findUserById(sellAgreement.getTargetID());
        //emailSender.sendEmail(validationUser.getEmail(), "registry: " + sellAgreement.getMortgageRegister());
        return hashService.getImageHash(documentImage);
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public boolean saveDocumentDetails(@RequestBody SellAgreement sellAgreement) {
        sellAgreementRepository.save(sellAgreement);
        return sellAgreementRepository.existsById(sellAgreement.getDocumentID());
    }
}

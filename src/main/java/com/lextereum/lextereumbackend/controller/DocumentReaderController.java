package com.lextereum.lextereumbackend.controller;

import com.lextereum.lextereumbackend.service.DocumentReaderService;
import lombok.RequiredArgsConstructor;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200") //TODO use docker
@RequestMapping("/documents")
public class DocumentReaderController {

    private final DocumentReaderService documentReader;

    @PutMapping("/read")
    public void readDocument(@RequestParam("documentImage") MultipartFile documentImage) throws TesseractException, IOException {
        documentReader.readDocument(documentImage.getBytes());
    }
}

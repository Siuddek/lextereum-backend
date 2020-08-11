package com.lextereum.lextereumbackend.controller;

import com.lextereum.lextereumbackend.service.DocumentReaderService;
import lombok.RequiredArgsConstructor;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/documents")
public class DocumentReaderController {

    private final DocumentReaderService documentReader;

    @PutMapping("/read")
    public void readDocument(byte[] documentImage) throws TesseractException {
        documentReader.readDocument(documentImage);
    }
}

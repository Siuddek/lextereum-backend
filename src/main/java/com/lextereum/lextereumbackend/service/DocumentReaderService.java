package com.lextereum.lextereumbackend.service;

import com.lextereum.lextereumbackend.service.parser.DocumentParserService;
import com.lextereum.lextereumbackend.service.parser.NameRepository;
import com.lextereum.lextereumbackend.utils.ByteOperationUtils;
import lombok.RequiredArgsConstructor;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.stereotype.Service;

import java.io.IOException;


@Service
@RequiredArgsConstructor
public class DocumentReaderService {

    private final Tesseract tesseract;
    private final DocumentParserService documentParserService;
    private final NameRepository nameRepository;

    public String readDocument(byte[] documentImage) throws TesseractException, IOException {
        String document = tesseract.doOCR(ByteOperationUtils.createImageFromBytes(documentImage));
        System.out.println(document);
        System.out.println("workinnggg " + nameRepository.findByName("Henryk"));
        // SellAgreement agreement = documentParserService.parseDocument(document);
        return document;
    }


}

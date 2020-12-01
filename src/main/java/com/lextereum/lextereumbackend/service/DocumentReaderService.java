package com.lextereum.lextereumbackend.service;

import com.lextereum.lextereumbackend.model.SellAgreementDto;
import com.lextereum.lextereumbackend.service.parser.DocumentParserService;
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

    public SellAgreementDto readDocument(byte[] documentImage) throws TesseractException, IOException {
        String document = tesseract.doOCR(ByteOperationUtils.createImageFromBytes(documentImage));
        return documentParserService.parseDocument(document);
    }


}

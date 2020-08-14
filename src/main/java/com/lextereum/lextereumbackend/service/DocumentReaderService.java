package com.lextereum.lextereumbackend.service;

import com.lextereum.lextereumbackend.utils.ByteOperationUtils;
import lombok.RequiredArgsConstructor;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class DocumentReaderService {

    private final Tesseract tesseract;

    public String readDocument(byte[] documentImage) throws TesseractException {
        String text = tesseract.doOCR(ByteOperationUtils.createImageFromBytes(documentImage));
        System.out.println(text);
        return text;
    }


}

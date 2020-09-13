package com.lextereum.lextereumbackend.service;

import com.lextereum.lextereumbackend.repositories.SellAgreementDto;
import net.sourceforge.tess4j.TesseractException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class DocumentReaderServiceTest {

    @Autowired
    private DocumentReaderService testObj;

    @Test
    public void documentReadTest() throws IOException, TesseractException {
        // given
        byte[] testImage = loadFile();

        //when
        SellAgreementDto sellAgreementDto = testObj.readDocument(testImage);

        // then
        assertNotNull(sellAgreementDto);
    }


    private byte[] loadFile() throws IOException {
        String testAgreementPath = "src/test/java/com/lextereum/lextereumbackend/testAssets/testAgreement_1PL.png";
        return Files.readAllBytes(Paths.get(testAgreementPath));
    }
}

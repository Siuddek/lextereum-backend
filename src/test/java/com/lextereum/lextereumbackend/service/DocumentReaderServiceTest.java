package com.lextereum.lextereumbackend.service;

import com.lextereum.lextereumbackend.model.SellAgreementDto;
import net.sourceforge.tess4j.TesseractException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class DocumentReaderServiceTest {

    @Autowired
    private DocumentReaderService testObj;

    @Test
    public void sellAgreementNotNullTest() throws IOException, TesseractException {
        // given
        byte[] testImage = loadFile();

        //when
        SellAgreementDto sellAgreement = testObj.readDocument(testImage);

        // then
        assertNotNull(sellAgreement);
    }

    @Test
    public void sellAgreementParsedWithProperValues() throws IOException, TesseractException {
        //given
        byte[] testImage = loadFile();
        String validSellerID = "12345678958";
        String validBuyerID = "33665549781";
        int price = 750000;

        //when
        SellAgreementDto sellAgreement = testObj.readDocument(testImage);

        //then
        assertEquals(validSellerID, sellAgreement.getSellerID());
        assertEquals(validBuyerID, sellAgreement.getBuyerID());
        assertEquals(price, sellAgreement.getPrice());
    }


    private byte[] loadFile() throws IOException {
        String testAgreementPath = "src/test/java/com/lextereum/lextereumbackend/testAssets/testAgreement_1PL.png";
        return Files.readAllBytes(Paths.get(testAgreementPath));
    }
}

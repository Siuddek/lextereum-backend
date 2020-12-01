package com.lextereum.lextereumbackend.repositories;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class SellAgreementRepositoryTest {
    @Autowired
    private SellAgreementRepository agreementRepository;

    @Test
    public void saveAgreementTest() {
        // given
        String documentId = "document id";
        SellAgreement sellAgreement = SellAgreement.builder()
                                                   .documentID(documentId)
                                                   .build();
        // when
        agreementRepository.save(sellAgreement);
        // then
        assertTrue(agreementRepository.existsById(documentId));
    }
}

package com.lextereum.lextereumbackend.service.parser;

import com.lextereum.lextereumbackend.repositories.SellAgreementDto;

public interface IDocumentParser {
    SellAgreementDto parseDocument(String document);
}

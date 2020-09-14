package com.lextereum.lextereumbackend.service.parser;

import com.lextereum.lextereumbackend.model.SellAgreementDto;

public interface IDocumentParser {
    SellAgreementDto parseDocument(String document);
}

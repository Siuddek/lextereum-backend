package com.lextereum.lextereumbackend.service.parser;

import com.lextereum.lextereumbackend.model.SellAgreement;

public interface IDocumentParser {
    SellAgreement parseDocument(String document);
}

package com.lextereum.lextereumbackend.service.parser.languageParser;

import com.lextereum.lextereumbackend.model.DocumentKeywords;
import com.lextereum.lextereumbackend.model.SellAgreement;
import com.lextereum.lextereumbackend.service.parser.GeneralParser;
import com.lextereum.lextereumbackend.service.parser.IDocumentParser;

import java.util.Optional;

public class PolishParser implements IDocumentParser {

    private final GeneralParser parser;

    public PolishParser(GeneralParser parser) {
        this.parser = parser;
    }

    @Override
    public SellAgreement parseDocument(String document) {
        DocumentKeywords documentKeywords = DocumentKeywords.builder()
                                                            .sellerKeywords(new String[]{"Sprzedawcą", "Sprzedawca"})
                                                            .sellerIDKeywords(new String[]{"PESEL"})
                                                            .buyerKeywords(new String[]{"Kupującym", "Kupujacym"})
                                                            .buyerIDKeywords(new String[]{"PESEL"})
                                                            .dateKeywords(new String[]{"Dnia", "Data"})
                                                            .squareMetersKeywords(new String[]{"powierzchni", "powierzchnia"})
                                                            .cityKeywords(new String[]{"miasto", "mieście", "miescie"})
                                                            .mortgageRegisterKeywords(new String[]{"numerem ", "numer ", "numerze: "})
                                                            .priceKeywords(new String[]{"cenę", "cene", "cena", "ceną"})
                                                            .downpaymentKeywords(new String[]{"zadatek", "zadatkiem", "zadatku"})
                                                            .mortgageRegex(Optional.of("\\w{4}\\/\\d{4,8}\\/\\d{1}"))
                                                            .priceRegex(Optional.of("\\d{4,}"))
                                                            .build();
        return parser.getParsedDocument(document, documentKeywords);
    }

}

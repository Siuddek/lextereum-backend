package com.lextereum.lextereumbackend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Optional;

@Data
@Builder
@AllArgsConstructor
public class DocumentKeywords {
    private String[] sellerKeywords;
    private String[] sellerIDKeywords;
    private String[] buyerKeywords;
    private String[] buyerIDKeywords;
    private String[] dateKeywords;
    private String[] squareMetersKeywords;
    private String[] cityKeywords;
    private String[] mortgageRegisterKeywords;
    private String[] priceKeywords;
    private String[] downpaymentKeywords;
    private Optional<String> mortgageRegex;
    private Optional<String> priceRegex;
}

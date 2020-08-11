package com.lextereum.lextereumbackend.model;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
public class SellAgreement {
    private final String seller;
    private final String sellerID;
    private final String buyer;
    private final String buyerID;
    private final String date;
    private final int squareMeters;
    private final String city;
    private final String mortgageRegister;
    private final int price;
    private final int downpayment;
}

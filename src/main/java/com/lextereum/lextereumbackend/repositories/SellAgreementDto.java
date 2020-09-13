package com.lextereum.lextereumbackend.repositories;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@Builder
@RequiredArgsConstructor
public class SellAgreementDto {
    private String seller;
    private String sellerID;
    private String buyer;
    private String buyerID;
    private String date;
    private int squareMeters;
    private String city;
    private String mortgageRegister;
    private int price;
    private int downpayment;
    private String documentID;
}

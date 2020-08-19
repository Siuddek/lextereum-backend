package com.lextereum.lextereumbackend.model;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@Builder
@RequiredArgsConstructor
public class SellAgreement {
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
}

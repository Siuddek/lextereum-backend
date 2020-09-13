package com.lextereum.lextereumbackend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SellAgreements")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SellAgreement {
    @Id
    private String documentID;
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

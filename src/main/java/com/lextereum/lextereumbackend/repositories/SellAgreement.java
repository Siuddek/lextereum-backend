package com.lextereum.lextereumbackend.repositories;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sellagreements")
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
    @Column(name = "squaremeters")
    private int squareMeters;
    private String city;
    @Column(name = "mortgageregister")
    private String mortgageRegister;
    private int price;
    private int downpayment;
    private String targetID;
    @Column(name = "documenthash")
    private String documentHash;
}

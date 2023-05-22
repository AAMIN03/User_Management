package com.springrest.User_Management.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long txnID;
    //private String Account_name;
    private long payer_mobileNo;
    private long payee_mobileNo;
    private long amount;
    private String status; //  Successful/Unsuccessful

}

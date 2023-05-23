package com.springrest.User_Management.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;


@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long txnID;
    //private String Account_name;

    private long payerid;
    private long payeeid;
    private BigDecimal amount;
    private String status; //  Success/failure

    public Transaction(long txnID, long  payeeid, long payerid,BigDecimal amount, String status) {
        super();
        this.txnID = txnID;
        this.payeeid = payeeid;
        this.payerid = payerid;
        this.amount = amount;
        this.status = status;
    }

    public Transaction() {
        super();
        // TODO Auto-generated constructor stub
    }

    public long getTxnID() {
        return txnID;
    }
    public void setTxnID(long txnID) {
        this.txnID = txnID;
    }

    public long getPayerid(){
        return payerid;
    }

    public void setPayerid(long payerid){
        this.payerid = payerid;
    }

    public long getPayeeid(){
        return payeeid;
    }

    public void setPayeeid(long payeeid){
        this.payeeid = payeeid;
    }

    public BigDecimal getAmount(){
        return amount;
    }

    public void setAmount(BigDecimal amount){
        this.amount = amount;
    }

    public String getStatus(){
        return status;
    }

    public void setStatus(String status){
        this.status = status;
    }

    @Override
    public String toString() {
        return "Transaction [txnID =" + txnID + ", payee_mobileNo=" + payeeid + ", payer_mobileNo=" + payerid + ", amount =" + amount +", status =" + status +"]";
    }

}

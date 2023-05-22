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

    public Transaction(long txnID, long  payer_mobileNo, long payee_mobileNo,long amount, String status) {
        super();
        this.txnID = txnID;
        this.payee_mobileNo = payee_mobileNo;
        this.payer_mobileNo = payer_mobileNo;
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

    public long getPayer_mobileNo(){
        return payer_mobileNo;
    }

    public void setPayer_mobileNo(long payer_mobileNo){
        this.payer_mobileNo = payer_mobileNo;
    }

    public long getPayee_mobileNo(){
        return payee_mobileNo;
    }

    public void setPayee_mobileNo(long payee_mobileNo){
        this.payee_mobileNo = payee_mobileNo;
    }

    public long getAmount(){
        return amount;
    }

    public void setAmount(long amount){
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
        return "Transaction [txnID =" + txnID + ", payee_mobileNo=" + payee_mobileNo + ", payer_mobileNo=" + payer_mobileNo + ", amount =" + amount +", status =" + status +"]";
    }

}

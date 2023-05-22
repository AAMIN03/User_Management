package com.springrest.User_Management.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;

@Entity
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userid;
    private long mobileNo;
    private BigDecimal Current_balance;

    public Wallet(long userid, long  mobileNo, BigDecimal Current_balance) {
        super();
        this.userid = userid;
        this.mobileNo = mobileNo;
        this.Current_balance = Current_balance;
    }

    public Wallet() {
        super();
        // TODO Auto-generated constructor stub
    }

    public long getUserid() {
        return userid;
    }
    public void setUserid(long userid) {
        this.userid = userid;
    }

    public long getMobileNo(){
        return mobileNo;
    }

    public void setMobileNo(long mobileNo){
        this.mobileNo = mobileNo;
    }

    public BigDecimal getCurrent_balance(){
        return Current_balance;
    }

    public void setCurrent_balance(BigDecimal Current_balance){
        this.Current_balance = Current_balance;
    }

    @Override
    public String toString() {
        return "Wallet [userid=" + userid + ", mobileNo=" + mobileNo + ", Current_balance=" + Current_balance + "]";
    }

}

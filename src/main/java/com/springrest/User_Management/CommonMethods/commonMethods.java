package com.springrest.User_Management.CommonMethods;

import com.springrest.User_Management.dao.TransactionDao;
import com.springrest.User_Management.entities.Transaction;
import com.springrest.User_Management.entities.Wallet;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

public class commonMethods {

    @Autowired
    public TransactionDao transactionDao;

    public Transaction successfulTransaction(long payerid,long payeeid, BigDecimal amount){
        Transaction transaction = new Transaction();
        transaction.setStatus("Success");
        transaction.setAmount(amount);
        transaction.setPayeeid(payeeid);
        transaction.setPayerid(payerid);
        return transaction;
    }

    public Transaction failTransaction(long payerid,long payeeid, BigDecimal amount){
        Transaction transaction = new Transaction();
        transaction.setStatus("failure");
        transaction.setAmount(amount);
        transaction.setPayeeid(payeeid);
        transaction.setPayerid(payerid);
        return transaction;
    }

    public Wallet walletForTesting(){
        Wallet wallet = new Wallet();
        wallet.setUserid(1L);
        wallet.setMobileNo("8743675420");
        wallet.setCurrent_balance(new BigDecimal(0));
        return wallet;
    }

}

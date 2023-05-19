package com.springrest.User_Management.services;

import com.springrest.User_Management.dao.TransactionDao;
import com.springrest.User_Management.dao.UserDao;
import com.springrest.User_Management.dao.WalletDao;
import com.springrest.User_Management.entities.Transaction;
import com.springrest.User_Management.entities.User;
import com.springrest.User_Management.entities.Wallet;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class WalletServiceImpl implements WalletService{

    @Autowired
    private TransactionDao transactionDao;

    @Autowired
    private WalletDao walletDao;

    @Override
    public Wallet createORupdate(Wallet wallet){
        walletDao.save(wallet);
        return wallet;
    }

    @Override
    public Transaction maketransaction(Transaction transaction){
        transactionDao.save(transaction);
        return transaction;
    }
}

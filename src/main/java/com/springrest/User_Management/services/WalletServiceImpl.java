package com.springrest.User_Management.services;

import com.springrest.User_Management.dao.TransactionDao;
import com.springrest.User_Management.dao.WalletDao;
import com.springrest.User_Management.entities.Transaction;
import com.springrest.User_Management.entities.Wallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public List<Transaction> getTransactions(long userid){
        List<Transaction> list=null;
        return list;
    }

    @Override
    public Optional<Transaction> gettransactionsummary(long txnid){
        Optional<Transaction> data = transactionDao.findById(txnid);
        return data;
    }

}

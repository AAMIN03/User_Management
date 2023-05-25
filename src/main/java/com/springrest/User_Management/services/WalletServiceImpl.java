package com.springrest.User_Management.services;

import com.springrest.User_Management.dao.TransactionDao;
import com.springrest.User_Management.dao.WalletDao;
import com.springrest.User_Management.entities.Transaction;
import com.springrest.User_Management.entities.Wallet;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
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
    public List<Transaction> getTransactionSummaryByUserId(long userid){

        List<Transaction> transactionList = new ArrayList<>();
        List<Transaction> payeeTransactions = transactionDao.getTransactionsByPayeeid(userid);
        transactionList.addAll(payeeTransactions);
        List<Transaction> payerTransactions = transactionDao.getTransactionsByPayerid(userid);
        transactionList.addAll(payerTransactions);

        return transactionList;
    }

    @Override
    public Optional<Transaction> getTransactionByTxnId(long txnId) {
        return transactionDao.findById(txnId);
    }


    @Override
    @Transactional
    public ResponseEntity <?> transfermoney(String payee_mobileNo, String payer_mobileNo, BigDecimal amount) {
        Wallet payerWallet = (Wallet) walletDao.findByMobileNo(String.valueOf(payer_mobileNo))
                .orElseThrow(() -> new IllegalArgumentException("Payer wallet not found"));

        Wallet payeeWallet = (Wallet) walletDao.findByMobileNo(String.valueOf(payee_mobileNo))
                .orElseThrow(() -> new IllegalArgumentException("Payee wallet not found"));

        BigDecimal payerBalance = payerWallet.getCurrent_balance();

        BigDecimal newPayerBalance = payerBalance.subtract(amount);
        payerWallet.setCurrent_balance(newPayerBalance);
        walletDao.save(payerWallet);

        BigDecimal payeeBalance = payeeWallet.getCurrent_balance();
        BigDecimal newPayeeBalance = payeeBalance.add(amount);
        payeeWallet.setCurrent_balance(newPayeeBalance);
        walletDao.save(payeeWallet);

        return new ResponseEntity<>("Money transferred successfully.", HttpStatus.OK);
    }

}

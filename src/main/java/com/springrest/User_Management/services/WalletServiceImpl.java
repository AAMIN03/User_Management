package com.springrest.User_Management.services;

import com.springrest.User_Management.dao.TransactionDao;
import com.springrest.User_Management.dao.WalletDao;
import com.springrest.User_Management.entities.Transaction;
import com.springrest.User_Management.entities.Wallet;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
        Wallet user = walletDao.getReferenceById(userid);
        List<Transaction> list= (List<Transaction>) transactionDao.findBypayeemobileNo(user.getMobileNo());
        return list;
    }

//    @Override
//    public Page<Transaction> getTransactionSummaryByUserId(Long userId, Pageable pageable) {
//        Wallet user = walletDao.getReferenceById(userId);
//        Page<Transaction> data = transactionDao.findBypayeemobileNo(user.getMobileNo(), pageable);
//        Page<Transaction> data2 = transactionDao.findBypayeemobileNo(user.getMobileNo(), pageable);
//        return transactionDao.findBypayeemobileNo(userId, pageable);
//    }

    @Override
    public Optional<Transaction> getTransactionByTxnId(long txnId) {
        return transactionDao.findById(txnId);
    }

    @Override
    @Transactional
    public Transaction maketransaction(Transaction transaction) {
        transaction.setStatus("Unsuccessful");
        Wallet payerWallet = (Wallet) walletDao.findByMobileNo(transaction.getPayer_mobileNo())
                .orElseThrow(() -> new IllegalArgumentException("Payer wallet not found"));

        Wallet payeeWallet = (Wallet) walletDao.findByMobileNo(transaction.getPayee_mobileNo())
                .orElseThrow(() -> new IllegalArgumentException("Payee wallet not found"));

        BigDecimal payerBalance = payerWallet.getCurrent_balance();
        if (payerBalance.compareTo(transaction.getAmount()) < 0) {
            throw new IllegalArgumentException("Insufficient balance in the payer wallet");
        }

        BigDecimal newPayerBalance = payerBalance.subtract(transaction.getAmount());
        payerWallet.setCurrent_balance(newPayerBalance);
        walletDao.save(payerWallet);

        BigDecimal payeeBalance = payeeWallet.getCurrent_balance();
        BigDecimal newPayeeBalance = payeeBalance.add(transaction.getAmount());
        payeeWallet.setCurrent_balance(newPayeeBalance);
        walletDao.save(payeeWallet);
        transaction.setStatus("Successful");

        return transaction;
    }

}

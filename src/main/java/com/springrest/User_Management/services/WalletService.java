package com.springrest.User_Management.services;

import com.springrest.User_Management.entities.Transaction;
import com.springrest.User_Management.entities.Wallet;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface WalletService {

    public Wallet createORupdate (Wallet wallet);

    Transaction maketransaction(Transaction transaction);


    //List<Transaction> getTransactionSummaryByUserId(Long userId);

    //Page<Transaction> getTransactionSummaryByUserId(Long userId, java.awt.print.Pageable pageable);

    List<Transaction> getTransactionSummaryByUserId(long userid);

    Optional<Transaction> getTransactionByTxnId(long txnId);
}

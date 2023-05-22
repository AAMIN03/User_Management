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

    List<Transaction> getTransactions(long userid);

    Optional<Transaction> gettransactionsummary(long txnid);
}

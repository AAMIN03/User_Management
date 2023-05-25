package com.springrest.User_Management.services;

import com.springrest.User_Management.entities.Transaction;
import com.springrest.User_Management.entities.Wallet;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public interface WalletService {

    public Wallet createORupdate (Wallet wallet);

    ResponseEntity <?> transfermoney(String payee_mobileNo, String payer_mobileNo, BigDecimal amount);


    List<Transaction> getTransactionSummaryByUserId(long userid);

    Optional<Transaction> getTransactionByTxnId(long txnId);
}

package com.springrest.User_Management.services;

import com.springrest.User_Management.dao.WalletDao;
import com.springrest.User_Management.entities.Transaction;
import com.springrest.User_Management.entities.Wallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface WalletService {

    public Wallet createORupdate (Wallet wallet);

    Transaction maketransaction(Transaction transaction);
}

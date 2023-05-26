package com.springrest.User_Management.services;

import com.springrest.User_Management.Common_Methods.common_methods;
import com.springrest.User_Management.dao.TransactionDao;
import com.springrest.User_Management.dao.WalletDao;
import com.springrest.User_Management.entities.Transaction;
import com.springrest.User_Management.entities.Wallet;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WalletServiceImplTest {

    @InjectMocks
    public WalletService walletServiceImpl = new WalletServiceImpl();

    @Mock
    private TransactionDao transactionDao;

    @Mock
    private WalletDao walletDao;

    @Test
    public void createORupdate() {
        Wallet wallet = common_methods.createWallet();
        when(walletDao.save(wallet)).thenReturn(wallet);
        assertThat(walletServiceImpl.createORupdate(wallet)).isEqualTo(wallet);
    }

    @Test
    public void addMoney(){
        String mobileNo = "7896572896";
        BigDecimal amount= BigDecimal.valueOf(567);
        Wallet wallet = common_methods.createWallet();
        when(walletDao.save(wallet)).thenReturn(wallet);
        when(walletDao.findByMobileNo(mobileNo)).thenReturn(Optional.of(wallet));
        BigDecimal currentbalance = wallet.getCurrent_balance();
        BigDecimal newBalance = currentbalance.add(amount);;
        assertThat(walletServiceImpl.addMoney(mobileNo,amount)).isEqualTo(newBalance);
    }

    @Test
    public void getTransactionSummaryByUserId(){
        long userid = 2;
        List<Transaction> transactionListAsPayee = new ArrayList<>();
        transactionListAsPayee.add()
        List<Transaction> transactionListAsPayer = new ArrayList<>();

    }

}
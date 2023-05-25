package com.springrest.User_Management.services;

import com.springrest.User_Management.Common_Methods.common_methods;
import com.springrest.User_Management.dao.TransactionDao;
import com.springrest.User_Management.dao.WalletDao;
import com.springrest.User_Management.entities.Wallet;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WalletServiceImplTest {

    @Autowired
    public WalletService walletService;
    @InjectMocks
    private WalletServiceImpl walletServiceImpl;

    @Mock
    private TransactionDao transactionDao;

    @Mock
    private WalletDao walletDao;

    @Test
    public void createORupdate() {
        Wallet wallet = common_methods.createWallet();
        when(walletDao.save(wallet)).thenReturn(wallet);
        assertThat(walletService.createORupdate(wallet)).isEqualTo(wallet);
    }

}
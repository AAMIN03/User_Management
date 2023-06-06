package com.springrest.User_Management.services;

import com.springrest.User_Management.CommonMethods.commonMethods;
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
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WalletServiceImplTest {

    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    public commonMethods commonmethods = new commonMethods();

    @InjectMocks
    public WalletService walletServiceImpl = new WalletServiceImpl();

    @Mock
    private TransactionDao transactionDao;

    @Mock
    private WalletDao walletDao;


    @Test
    public void createORupdate() {
        Wallet wallet = common_methods.createWallet();

        //mocking walletDao save method
        when(walletDao.save(wallet)).thenReturn(wallet);
        assertThat(walletServiceImpl.createORupdate(wallet)).isEqualTo(wallet);
    }

    @Test
    public void addMoney(){
        String mobileNo = "7896572896";
        BigDecimal amount= BigDecimal.valueOf(567);
        Wallet wallet = common_methods.createWallet();

        //mocking walletDao save and findByMobileNo method
        when(walletDao.save(wallet)).thenReturn(wallet);
        when(walletDao.findByMobileNo(mobileNo)).thenReturn(Optional.of(wallet));

        BigDecimal currentbalance = wallet.getCurrent_balance();
        BigDecimal newBalance = currentbalance.add(amount);
        assertThat(walletServiceImpl.addMoney(mobileNo,amount)).isEqualTo(newBalance);
    }

    @Test
    public void getTransactionSummaryByUserId(){

        List<Transaction> transactionList = new ArrayList<>();

        Transaction TransactionAsPayee = commonmethods.successfulTransaction(123L,456L,new BigDecimal(100));
        List<Transaction> transactionListAsPayee = new ArrayList<>();
        transactionListAsPayee.add(TransactionAsPayee);

        Transaction TransactionAsPayer =  commonmethods.failTransaction(456L,123L,new BigDecimal(100000000));
        List<Transaction> transactionListAsPayer = new ArrayList<>();
        transactionListAsPayee.add(TransactionAsPayer);

        transactionList.add(TransactionAsPayee);
        transactionList.add(TransactionAsPayer);

        //mocking transactionDao getTransactionsByPayeeid and getTransactionsByPayerid
        when(transactionDao.getTransactionsByPayeeid(123)).thenReturn(transactionListAsPayee);
        when(transactionDao.getTransactionsByPayerid(123)).thenReturn(transactionListAsPayer);

        assertThat(walletServiceImpl.getTransactionSummaryByUserId(123L)).isEqualTo(transactionList);
    }

    @Test
    public void getTransactionByTxnId(){
        long txnid = 123;
        Optional<Transaction> transaction = Optional.ofNullable(commonmethods.successfulTransaction(123L, 456L, new BigDecimal(100)));

        //mocking transactionDao findById() method
        when(transactionDao.findById(txnid)).thenReturn(transaction);
        assertThat(walletServiceImpl.getTransactionByTxnId(txnid)).isEqualTo(transaction);
    }

    @Test
    void transferMoney_SuccessfullyTransferred() {
        // Mocking the payer wallet
        Wallet payerWallet = new Wallet();
        payerWallet.setMobileNo("1234567890");
        payerWallet.setCurrent_balance(new BigDecimal("500.00"));
        when(walletDao.findByMobileNo("1234567890")).thenReturn(Optional.of(payerWallet));

        // Mocking the payee wallet
        Wallet payeeWallet = new Wallet();
        payeeWallet.setMobileNo("9876543210");
        payeeWallet.setCurrent_balance(new BigDecimal("100.00"));
        when(walletDao.findByMobileNo("9876543210")).thenReturn(Optional.of(payeeWallet));

        // Mocking the walletDao save method
        when(walletDao.save(any())).thenReturn(null);

        // Transfer money
        ResponseEntity<?> response = walletServiceImpl.transfermoney("9876543210", "1234567890", new BigDecimal("200.00"));

        // Assertions
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Money transferred successfully.", response.getBody());

        // Verify walletDao methods were called
        verify(walletDao, times(2)).findByMobileNo(anyString());
        verify(walletDao, times(2)).save(any());
    }

    @Test
    void transferMoney_PayerWalletNotFound_ThrowsException() {
        // Mocking the payer wallet not found scenario
        when(walletDao.findByMobileNo("1234567890")).thenReturn(Optional.empty());

        // Transfer money and expect an exception to be thrown
        assertThrows(IllegalArgumentException.class,
                () -> walletServiceImpl.transfermoney("9876543210", "1234567890", new BigDecimal("200.00")));

        // Verify walletDao method was called
        verify(walletDao).findByMobileNo(anyString());
        verify(walletDao, never()).save(any());
    }

    @Test
    void transferMoney_PayeeWalletNotFound_ThrowsException() {
        // Mocking the payee wallet not found scenario
        Wallet payerWallet = new Wallet();
        payerWallet.setMobileNo("1234567890");
        when(walletDao.findByMobileNo("1234567890")).thenReturn(Optional.of(payerWallet));
        when(walletDao.findByMobileNo("9876543210")).thenReturn(Optional.empty());

        // Transfer money and expect an exception to be thrown
        assertThrows(IllegalArgumentException.class,
                () -> walletServiceImpl.transfermoney("9876543210", "1234567890", new BigDecimal("200.00")));

        // Verify walletDao methods were called
        verify(walletDao).findByMobileNo("9876543210");
        verify(walletDao, never()).save(any());
    }


}
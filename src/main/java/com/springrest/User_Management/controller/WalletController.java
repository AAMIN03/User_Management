package com.springrest.User_Management.controller;

import com.springrest.User_Management.dao.TransactionDao;
import com.springrest.User_Management.dao.UserDao;
import com.springrest.User_Management.dao.WalletDao;
import com.springrest.User_Management.entities.Transaction;
import com.springrest.User_Management.entities.User;
import com.springrest.User_Management.entities.Wallet;
import com.springrest.User_Management.services.WalletService;
import com.springrest.User_Management.CommonMethods.commonMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

//import static org.springframework.http.converter.ResourceRegionHttpMessageConverter.print;

@RestController
public class WalletController {

    public commonMethods commonmethods = new commonMethods();
    @Autowired
    public WalletDao walletDao;

    @Autowired
    public TransactionDao transactionDao;

    @Autowired
    public WalletService walletService;

    @GetMapping("/Wallet_home")
    public String Wallet_home() {
        return "Welcome to the Wallet Management portal.";
    }

	@PostMapping("/wallet")
	public ResponseEntity <?> createORupdate(@RequestBody Wallet wallet){
        if(!walletDao.existsByMobileNo(wallet.getMobileNo())) {
            Wallet walletSaved = this.walletService.createORupdate(wallet);
            return new ResponseEntity<>(wallet, HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Wallet already exists with requested mobile no.",HttpStatus.BAD_REQUEST);
	}

    @PostMapping("/Transaction")
    public ResponseEntity <?> transaction (@RequestParam("payee_mobileNo") long payee_mobileNo,
                                           @RequestParam("payer_mobileNo") long payer_mobileNo,
                                           @RequestParam("amount") BigDecimal amount ){

        if(!walletDao.existsByMobileNo(payee_mobileNo)){
            return new ResponseEntity<>("Wallet not exists with requested payee_mobileNo.",HttpStatus.NOT_FOUND);
        }

        if(!walletDao.existsByMobileNo(payer_mobileNo)){
            return new ResponseEntity<>("Wallet not exists with requested payer_mobileNo.",HttpStatus.NOT_FOUND);
        }

        Wallet payerWallet = (Wallet) walletDao.findByMobileNo(payer_mobileNo)
                .orElseThrow(() -> new IllegalArgumentException("Payer wallet not found"));

        BigDecimal payerBalance = payerWallet.getCurrent_balance();

        Wallet payeeWallet = (Wallet) walletDao.findByMobileNo(payee_mobileNo)
                .orElseThrow(() -> new IllegalArgumentException("Payee wallet not found"));

        if (payerBalance.compareTo(amount) < 0) {
            Transaction transaction = commonmethods.failTransaction(payerWallet.getUserid(),payeeWallet.getUserid(),amount);
            transactionDao.save(transaction);
            return new ResponseEntity<>("Payer doesn't have sufficient amount.",HttpStatus.BAD_REQUEST);
        }
        Transaction transaction = commonmethods.successfulTransaction(payerWallet.getUserid(),payeeWallet.getUserid(),amount);
        transactionDao.save(transaction);

        ResponseEntity <?> response = this.walletService.transfermoney(payee_mobileNo,payer_mobileNo,amount);
        return new ResponseEntity<>(response,HttpStatus.OK);

    }

    @GetMapping("/Transaction/Summary")
    public ResponseEntity <?> getTransactions (@RequestParam("userid") long userid){
        if(!walletDao.existsById(userid)){
            return new ResponseEntity<>("User not exists.",HttpStatus.NOT_FOUND);
        }
        List<Transaction> transactions = this.walletService.getTransactionSummaryByUserId(userid);
        return new ResponseEntity<>(transactions,HttpStatus.OK);
    }


    @GetMapping("Transaction")
    public ResponseEntity <?> transactionStatus (@RequestParam("txnid") long txnid){
        if(!transactionDao.existsById(txnid)){
            return new ResponseEntity<>("Transaction not exists with requested txnid.",HttpStatus.NOT_FOUND);
        }
        Optional<Transaction> transaction = this.walletService.getTransactionByTxnId(txnid);
        return new ResponseEntity<>(transaction,HttpStatus.OK);
    }

}
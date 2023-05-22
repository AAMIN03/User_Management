package com.springrest.User_Management.controller;

import com.springrest.User_Management.dao.TransactionDao;
import com.springrest.User_Management.dao.UserDao;
import com.springrest.User_Management.dao.WalletDao;
import com.springrest.User_Management.entities.Transaction;
import com.springrest.User_Management.entities.User;
import com.springrest.User_Management.entities.Wallet;
import com.springrest.User_Management.services.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

//import static org.springframework.http.converter.ResourceRegionHttpMessageConverter.print;

@RestController
public class WalletController {

    public TransactionDao transactionDao;
    public WalletDao walletDao;
    //public UserDao userDao;
    @Autowired
    public WalletService walletService;

    @GetMapping("/Wallet_home")
    public String Wallet_home() {
        return "Welcome to the Wallet Management portal.";
    }

	@PostMapping("/wallet")
	public ResponseEntity <?> createORupdate(@RequestBody Wallet wallet){
        Wallet user = (Wallet) walletDao.findByMobileNo(wallet.getMobileNo()).orElseThrow(() -> new IllegalArgumentException("Payer wallet not found"));
        Wallet walletSaved = this.walletService.createORupdate(wallet);
			return new ResponseEntity<>(wallet, HttpStatus.CREATED);
	}


    @PostMapping("/Transaction")
    public ResponseEntity <?> transaction (@RequestBody Transaction transaction1){
        Transaction transactionSaved = this.walletService.maketransaction(transaction1);
        return new ResponseEntity<>(transaction1,HttpStatus.OK);
    }

    @GetMapping("/Transaction/{userid}")
    public ResponseEntity <?> getTransactions (@PathVariable long userid){
        List<Transaction> transactions = this.walletService.getTransactionSummaryByUserId(userid);
        return new ResponseEntity<>(transactions,HttpStatus.OK);
    }

//    @GetMapping("/Transaction/{userid}")
//    public Page<Transaction> getTransactionSummary(
//            @RequestParam("userId") Long userId,
//            @RequestParam(value = "page", defaultValue = "0") int page,
//            @RequestParam(value = "size", defaultValue = "10") int size) {
//        PageRequest pageable = PageRequest.of(page, size);
//        return walletService.getTransactionSummaryByUserId(userId, (Pageable) pageable);
//    }

    @GetMapping("/Transaction/{txnID}")
    public ResponseEntity <?> transactionStatus (@PathVariable long txnid){
        Optional<Transaction> transaction = this.walletService.getTransactionByTxnId(txnid);
        return new ResponseEntity<>(transaction,HttpStatus.OK);
    }

}
package com.springrest.User_Management.controller;

import com.springrest.User_Management.dao.TransactionDao;
import com.springrest.User_Management.dao.UserDao;
import com.springrest.User_Management.dao.WalletDao;
import com.springrest.User_Management.entities.Transaction;
import com.springrest.User_Management.entities.User;
import com.springrest.User_Management.entities.Wallet;
import com.springrest.User_Management.services.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        //Optional<Wallet> existingUser = walletDao.findBymobileNo(wallet.getMobileNo());
		if (!walletDao.existsByMobileNo(wallet.getMobileNo())) {
			Wallet walletSaved = this.walletService.createORupdate(wallet);
			return new ResponseEntity<>(wallet, HttpStatus.CREATED);
		}
		return new ResponseEntity<>("User already exists.", HttpStatus.BAD_REQUEST);
	}


    @PostMapping("/Transaction")
    public ResponseEntity <?> transaction (@RequestBody Transaction transaction1){
        //Optional<Wallet> payer = walletDao.findBymobileNo(transaction1.getPayer_mobileNo());
        //if(transactionDao.existsByMobileNo(transaction1.getPayer_mobileNo())&& transactionDao.existsByMobileNo(transaction1.getPayee_mobileNo())&&payer.ge)
        Transaction transactionSaved = this.walletService.maketransaction(transaction1);
        //transactionDao.save(transaction1);
        return new ResponseEntity<>(transaction1,HttpStatus.OK);
    }

    @GetMapping("/Transaction/{userid}")
    public ResponseEntity <?> gettransactions (@PathVariable long userid){
        List<Transaction> transactions = this.walletService.getTransactions(userid);
        return new ResponseEntity<>(transactions,HttpStatus.OK);
    }

    @GetMapping("/Transaction/{txnID}")
    public ResponseEntity <?> transactionSummery (@PathVariable long txnid){
        Optional<Transaction> transaction = this.walletService.gettransactionsummary(txnid);
        return new ResponseEntity<>(transaction,HttpStatus.OK);
    }

}
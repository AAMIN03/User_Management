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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

//	@PostMapping("/wallet")
//	public ResponseEntity <?> createORupdate(@RequestBody Wallet wallet){
//		//System.out.println("kjdfdbfid");
//		System.out.println(wallet);
//		//if (!walletDao.existsByMobileNo(wallet.getMobileNo())) {
//			//Wallet walletSaved = this.walletService.createORupdate(wallet);
//			return new ResponseEntity<>(wallet, HttpStatus.CREATED);
//		//}
//		//return new ResponseEntity<>("User already exists.", HttpStatus.BAD_REQUEST);
//	}

	@PostMapping("/wallet")
	public ResponseEntity<?> addUser(@RequestBody Wallet user) {
		System.out.println(user);
		//return this.userService.addUser(user);
		return new ResponseEntity<>(user, HttpStatus.CREATED);
	}

//	@PostMapping("/Transaction")
//	public ResponseEntity <?> transaction(@RequestBody Transaction transaction){
//		var x = walletDao.find
//		if(transactionDao.existsByMobileNo(transaction.getPayee_mobileNo())&&transactionDao.existsByMobileNo(transaction.getPayer_mobileNo())&&walletDao.)
//		Transaction transaction1 = this.walletService.maketransaction(transaction);
//		return new ResponseEntity<Transaction>(transaction1,HttpStatus.CREATED);
//	}

}

package com.springrest.User_Management.controller;

import com.springrest.User_Management.MessageRequest;
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
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.awt.print.Pageable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

//import static org.springframework.http.converter.ResourceRegionHttpMessageConverter.print;

@RestController
//@Validated
public class WalletController {

    public commonMethods commonmethods = new commonMethods();

    private KafkaTemplate<String,String> kafkaTemplate;

    public WalletController(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

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
	public ResponseEntity <?> createORupdate(@Valid @RequestBody Wallet wallet){
        String regexp = "\\d{10}";
        if(!wallet.getMobileNo().matches(regexp)){
            return new ResponseEntity<>("Invalid mobile no.", HttpStatus.BAD_REQUEST);
        }
        if(!walletDao.existsByMobileNo(wallet.getMobileNo())) {
            Wallet walletSaved = this.walletService.createORupdate(wallet);
            kafkaTemplate.send("NewTopic1", "Wallet created: "+ String.valueOf(wallet));
            return new ResponseEntity<>(wallet, HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Wallet already exists with requested mobile no.",HttpStatus.BAD_REQUEST);
	}

    @PostMapping("api/v1/messages")
    public void publish(@RequestBody MessageRequest request){
        kafkaTemplate.send("NewTopic1", request.message());
    }

    @PostMapping("/wallet/addMoney")
    public ResponseEntity<?> addMoney(@RequestBody Map<String,Object> requestBody){
        String mobileNo = (String) requestBody.get("mobileNo");
        BigDecimal amount = new BigDecimal(requestBody.get("amount").toString());
        String regexp = "\\d{10}";
        if(!mobileNo.matches(regexp)){
            return new ResponseEntity<>("Invalid mobile no.", HttpStatus.BAD_REQUEST);
        }

        if(walletDao.existsByMobileNo(mobileNo)) {
            BigDecimal newBalance = this.walletService.addMoney(mobileNo,amount);
            kafkaTemplate.send("NewTopic1", "Money successfully added to the wallet. New balance is: " + newBalance + ".");
            return new ResponseEntity<>("Money successfully added to the wallet. New balance is: " + newBalance + ".", HttpStatus.OK);
        }
        return new ResponseEntity<>("Wallet not exists with requested mobile no.", HttpStatus.BAD_REQUEST);

    }

    @PostMapping("/Transaction")
    public ResponseEntity <?> transaction (@RequestBody Map<String, Object> requestBodyData){

        String payee_mobileNo = (String) requestBodyData.get("payee_mobileNo");
        String payer_mobileNo = (String) requestBodyData.get("payer_mobileNo");

        String regexp = "\\d{10}";
        if(!payee_mobileNo.matches(regexp)){
            return new ResponseEntity<>("Invalid payee mobile no.", HttpStatus.BAD_REQUEST);
        }

        if(!payer_mobileNo.matches(regexp)){
            return new ResponseEntity<>("Invalid payer mobile no.", HttpStatus.BAD_REQUEST);
        }

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

        BigDecimal amount = new BigDecimal(requestBodyData.get("amount").toString());

        if (payerBalance.compareTo(amount) < 0) {
            Transaction transaction = commonmethods.failTransaction(payerWallet.getUserid(),payeeWallet.getUserid(),amount);
            transactionDao.save(transaction);
            System.out.println(transaction);
            kafkaTemplate.send("NewTopic1", "Transaction, Failure: "+ String.valueOf(transaction));
            return new ResponseEntity<>("Payer doesn't have sufficient amount.",HttpStatus.BAD_REQUEST);
        }

        Transaction transaction = commonmethods.successfulTransaction(payerWallet.getUserid(),payeeWallet.getUserid(),amount);
        transactionDao.save(transaction);
        kafkaTemplate.send("NewTopic1", "Transaction, Success: "+ String.valueOf(transaction));

        ResponseEntity <?> response = this.walletService.transfermoney(payee_mobileNo,payer_mobileNo,amount);
        return new ResponseEntity<>(response,HttpStatus.OK);

    }

    @GetMapping("/Transaction/Summary")
    public ResponseEntity <?> getTransactions (@RequestBody Map<String,Object> requestBody){
        Object valueObject = requestBody.get("userid");
        long userid;

        if (valueObject instanceof Long) {
            userid = (long) valueObject;
        } else if (valueObject instanceof Integer) {
            userid = ((Integer) valueObject).longValue();
        } else {
            return ResponseEntity.badRequest().body("Invalid value type. Expected long or int.");
        }

        if(!walletDao.existsById(userid)){
            return new ResponseEntity<>("User not exists.",HttpStatus.NOT_FOUND);
        }
        List<Transaction> transactions = this.walletService.getTransactionSummaryByUserId(userid);
        return new ResponseEntity<>(transactions,HttpStatus.OK);
    }

    @GetMapping("/Transaction")
    //@PreAuthorize("hasAuthority('ROLE_ADMIN'/ROLE_USER)")
    public ResponseEntity <?> transactionStatus (@RequestBody Map<String,Object> requestBody){

        Object valueObject = requestBody.get("txnid");
        long txnid;

        if (valueObject instanceof Long) {
            txnid = (long) valueObject;
        } else if (valueObject instanceof Integer) {
            txnid = ((Integer) valueObject).longValue();
        } else {
            return ResponseEntity.badRequest().body("Invalid value type. Expected long or int.");
        }

        if(!transactionDao.existsById(txnid)){
            return new ResponseEntity<>("Transaction not exists with requested txnid.",HttpStatus.NOT_FOUND);
        }
        Optional<Transaction> transaction = this.walletService.getTransactionByTxnId(txnid);
        return new ResponseEntity<>(transaction,HttpStatus.OK);
    }

}
package com.springrest.User_Management.dao;

import com.springrest.User_Management.entities.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionDao extends JpaRepository <Transaction,Long> {

    List<Transaction> getTransactionsByPayeeid(long userid);

    List<Transaction> getTransactionsByPayerid(long userid);
}

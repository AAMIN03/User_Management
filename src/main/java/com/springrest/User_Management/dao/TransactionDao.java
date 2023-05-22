package com.springrest.User_Management.dao;

import com.springrest.User_Management.entities.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionDao extends JpaRepository <Transaction,Long> {
    Page<Transaction> findBypayeemobileNo(Long userId);
    //boolean existsByMobileNo(long mobileNo);
}

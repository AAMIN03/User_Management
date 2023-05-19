package com.springrest.User_Management.dao;

import com.springrest.User_Management.entities.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletDao extends JpaRepository <Wallet,Long> {

}

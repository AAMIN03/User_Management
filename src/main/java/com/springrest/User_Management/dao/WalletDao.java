package com.springrest.User_Management.dao;

import com.springrest.User_Management.entities.User;
import com.springrest.User_Management.entities.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WalletDao extends JpaRepository <Wallet,Long> {

    boolean existsByMobileNo(String mobileNo);

    Optional<Wallet> findBymobileNo(String mobileNo);

    Optional<Object> findByMobileNo(String MobileNo);
}

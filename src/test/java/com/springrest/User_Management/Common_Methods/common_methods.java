package com.springrest.User_Management.Common_Methods;

import com.springrest.User_Management.entities.User;
import com.springrest.User_Management.entities.Wallet;

import java.math.BigDecimal;

public class common_methods {

    public static User getuser(){
        User user = new User();
        user.setid("abc");
        user.setEmail("abc@gmail.com");
        user.setAddress1("abc,sdk,dsk");
        user.setAddress2("abc,xcjgd,sdh");
        user.setMobileNo("7390937904");
        user.setFirstname("abcdvr");
        user.setLastname("dsdsfd");
        return user;
    }

    public static Wallet createWallet(){
        Wallet wallet = new Wallet();
        wallet.setMobileNo("7046757423");
        wallet.setCurrent_balance( BigDecimal.ZERO);
        wallet.setUserid(6);
        return wallet;
    }
}

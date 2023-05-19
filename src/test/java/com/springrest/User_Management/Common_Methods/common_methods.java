package com.springrest.User_Management.Common_Methods;

import com.springrest.User_Management.entities.User;

public class common_methods {

    public static User getuser(){
        User user = new User();
        user.setid("abc");
        user.setEmail("abc@gmail.com");
        user.setAddress1("abc,sdk,dsk");
        user.setAddress2("abc,xcjgd,sdh");
        user.setMobileNo(7390937904L);
        user.setFirstname("abcdvr");
        user.setLastname("dsdsfd");
        return user;
    }
}

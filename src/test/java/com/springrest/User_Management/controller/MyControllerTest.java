package com.springrest.User_Management.controller;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyControllerTest {

    @Test
    void home(){
        var homemethod = new MyController();
        assertEquals("Welcome to the User Management portal.",homemethod.home());
    }

}
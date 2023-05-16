package com.springrest.User_Management.services;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springrest.User_Management.dao.UserDao;
import com.springrest.User_Management.services.UserService;
import com.sun.source.tree.ModuleTree;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import com.springrest.User_Management.entities.User;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@WebMvcTest
class UserServiceImplTest {

    ObjectMapper mapper = new ObjectMapper();

    @Mock
    private UserDao userDao;


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;


    List <User> list,list1;


    @Before
    public void setUp(){
        list = new ArrayList<>();
        list.add(new User("aab","Aamin","Chaudhari",7046757423L,"aaminchaudhari@gmail.com","hcgjc","hgjsh"));
    }


    @Test
    public void getUsers() throws Exception {

        list1 = new ArrayList<>();
        User mockUser = new User();
        mockUser.setid("abc");
        mockUser.setEmail("abc@gmail.com");
        mockUser.setAddress1("abc,sdk,dsk");
        mockUser.setAddress2("abc,xcjgd,sdh");
        mockUser.setMobileNo(7390937904L);
        mockUser.setFirstname("abcdvr");
        mockUser.setLastname("dsdsfd");

        list1.add(mockUser);

        Mockito.when(userDao.findAll()).thenReturn(list1);
        assertThat(userDao.findAll()).isEqualTo(list1);

    }

    @Test
    void getUser() throws Exception {
        User mockUser = new User();
        mockUser.setid("abc");
        mockUser.setEmail("abc@gmail.com");
        mockUser.setAddress1("abc,sdk,dsk");
        mockUser.setAddress2("abc,xcjgd,sdh");
        mockUser.setMobileNo(7390937904L);
        mockUser.setFirstname("abcdvr");
        mockUser.setLastname("dsdsfd");

        Mockito.when(userDao.findById(mockUser.getid())).thenReturn(Optional.ofNullable(mockUser));
        assertThat(userDao.findById(mockUser.getid())).isEqualTo(Optional.ofNullable(mockUser));

    }

    @Test
    void addUser() throws Exception {
        User mockUser = new User();
        mockUser.setid("abc");
        mockUser.setEmail("abc@gmail.com");
        mockUser.setAddress1("abc,sdk,dsk");
        mockUser.setAddress2("abc,xcjgd,sdh");
        mockUser.setMobileNo(7390937904L);
        mockUser.setFirstname("abcdvr");
        mockUser.setLastname("dsdsfd");

        //System.out.println(mockUser);



        Mockito.when(userDao.save(mockUser)).thenReturn(mockUser);
        assertThat(userDao.save(mockUser)).isEqualTo(mockUser);
    }

    @Test
    void updateUser() {
        User mockUser = new User();
        mockUser.setid("abc");
        mockUser.setEmail("abc@gmail.com");
        mockUser.setAddress1("abc,sdk,dsk");
        mockUser.setAddress2("abc,xcjgd,sdh");
        mockUser.setMobileNo(7390937904L);
        mockUser.setFirstname("abcdvr");
        mockUser.setLastname("dsdsfd");

        Mockito.when(userService.updateUser(mockUser.getid(),mockUser)).thenReturn(new ResponseEntity<>(HttpStatus.OK));
        assertThat(userService.updateUser(mockUser.getid(),mockUser)).isEqualTo(new ResponseEntity<>(HttpStatus.OK));

    }

    @Test
    void deleteUser(){
        User mockUser = new User();
        mockUser.setid("abc");
        mockUser.setEmail("abc@gmail.com");
        mockUser.setAddress1("abc,sdk,dsk");
        mockUser.setAddress2("abc,xcjgd,sdh");
        mockUser.setMobileNo(7390937904L);
        mockUser.setFirstname("abcdvr");
        mockUser.setLastname("dsdsfd");

        Mockito.when(userDao.getReferenceById(mockUser.getid())).thenReturn(mockUser);
        Mockito.when(userService.deleteUser(mockUser.getid())).thenReturn(new ResponseEntity<>(HttpStatus.OK));
        assertThat(userService.deleteUser(mockUser.getid())).isEqualTo(new ResponseEntity<>(HttpStatus.OK));
    }
}
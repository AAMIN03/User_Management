package com.springrest.User_Management.services;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springrest.User_Management.Common_Methods.common_methods;
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

@SpringBootTest
class UserServiceImplTest {

    ObjectMapper mapper = new ObjectMapper();

    public common_methods Common_methods;

    @MockBean
    private UserDao userDao;


//    @Autowired
//    private MockMvc mockMvc;

    @Autowired
    public UserService userService;

    List <User> list,list1;


    @Before
    public void setUp(){
        list = new ArrayList<>();
        list.add(new User("aab","Aamin","Chaudhari",7046757423L,"aaminchaudhari@gmail.com","hcgjc","hgjsh"));
    }


    @Test
    public void getUsers() throws Exception {

        list1 = new ArrayList<>();
        User mockUser = common_methods.getuser();
//        User mockUser = new User();
//        mockUser.setid("abc");
//        mockUser.setEmail("abc@gmail.com");
//        mockUser.setAddress1("abc,sdk,dsk");
//        mockUser.setAddress2("abc,xcjgd,sdh");
//        mockUser.setMobileNo(7390937904L);
//        mockUser.setFirstname("abcdvr");
//        mockUser.setLastname("dsdsfd");

        list1.add(mockUser);

        Mockito.when(userDao.findAll()).thenReturn(list1);
        assertThat(userService.getUsers()).isEqualTo(list1);

    }

    @Test
    void getUser() throws Exception {

        User mockUser = common_methods.getuser();

        // Act
        Mockito.when(userDao.findById(mockUser.getid())).thenReturn(Optional.of(mockUser));
        ResponseEntity<?> response = userService.getUser(mockUser.getid());

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(response.getBody(),mockUser);
    }

    @Test
    void addUser() throws Exception {
        User mockUser = common_methods.getuser();
        String message = mockUser + "  added successfully.";

        // Act
        Mockito.when(userDao.save(mockUser)).thenReturn(mockUser);
        ResponseEntity<?> response = userService.addUser(mockUser);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(response.getBody(),message);
    }

    @Test
    void updateUser() {

        User mockUser = common_methods.getuser();

        // Act
        Mockito.when(userDao.findById(mockUser.getid())).thenReturn(Optional.of(mockUser));
        ResponseEntity<?> response = userService.updateUser(mockUser.getid(),mockUser);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNull(response.getBody());

    }

    @Test
    void deleteUser(){
        User mockUser = common_methods.getuser();

//        Mockito.when(userDao.getReferenceById(mockUser.getid())).thenReturn(mockUser);
//        Mockito.when(userService.deleteUser(mockUser.getid())).thenReturn(new ResponseEntity<>(HttpStatus.OK));
//        assertThat(userService.deleteUser(mockUser.getid())).isEqualTo(new ResponseEntity<>(HttpStatus.OK));

//        {
//            "id": "abc",
//                "firstname": "Aamin",
//                "lastname": "Chaudhari",
//                "mobileNo": 1298061,
//                "email": "hjk.com",
//                "address1": "hcgjc",
//                "address2": "nf updated"
//        }

//        User mockUser = new User();
//        mockUser.setid("abc");
//        mockUser.setEmail("hjk.com");
//        mockUser.setAddress1("hcgjc");
//        mockUser.setAddress2("nf updated");
//        mockUser.setMobileNo(1298061L);
//        mockUser.setFirstname("Aamin");
//        mockUser.setLastname("Chaudhari");

        // Act
        Mockito.when(userDao.getReferenceById(mockUser.getid())).thenReturn(mockUser);
        ResponseEntity<?> response = userService.deleteUser(mockUser.getid());

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNull(response.getBody());

    }

}
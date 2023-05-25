package com.springrest.User_Management.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springrest.User_Management.services.UserService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import com.springrest.User_Management.entities.User;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.springrest.User_Management.Common_Methods.common_methods;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@WebMvcTest
class MyControllerTest {

    ObjectMapper mapper = new ObjectMapper();

    public common_methods Common_methods;

    @Autowired
    private MockMvc mockMvc;

    List <User> list,list1;

    @MockBean
    private UserService userService;

    @Before
    public void setUp(){
        list = new ArrayList<>();
        list.add(new User("aab","Aamin","Chaudhari","7046757423","aaminchaudhari@gmail.com","hcgjc","hgjsh"));
        //System.out.println(list);
    }

    @Test
    public void home(){
        var controller = new MyController();
        assertEquals("Welcome to the User Management portal.",controller.home());
    }

    @Test
    public void getusers() throws Exception{

        list1 = new ArrayList<>();
        //User mockUser = new User();
        User mockUser = common_methods.getuser();
//        mockUser.setid("abc");
//        mockUser.setEmail("abc@gmail.com");
//        mockUser.setAddress1("abc,sdk,dsk");
//        mockUser.setAddress2("abc,xcjgd,sdh");
//        mockUser.setMobileNo(7390937904L);
//        mockUser.setFirstname("abcdvr");
//        mockUser.setLastname("dsdsfd");

        list1.add(mockUser);

        String inputInJson = mapper.writeValueAsString(list1);
        String URI = "/users";
        Mockito.when(userService.getUsers()).thenReturn((list1));
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String expectedJson = mapper.writeValueAsString(list1);
        String outputInJson = result.getResponse().getContentAsString();
//        System.out.println(list);
//        System.out.println(expectedJson);
//        System.out.println(outputInJson);
        assertEquals(expectedJson,outputInJson);
    }

    @Test
    public void getuser() throws Exception{

        User mockUser = common_methods.getuser();

        //list1 = new ArrayList<>();
//        User mockUser = new User();
//        mockUser.setid("abc");
//        mockUser.setEmail("abc@gmail.com");
//        mockUser.setAddress1("abc,sdk,dsk");
//        mockUser.setAddress2("abc,xcjgd,sdh");
//        mockUser.setMobileNo(7390937904L);
//        mockUser.setFirstname("abcdvr");
//        mockUser.setLastname("dsdsfd");

        //list1.add(mockUser);

        String inputInJson = mapper.writeValueAsString(mockUser);
        String id = "abc";
        String URI = "/users/{id}";
        ResponseEntity<?> mockResponseEntity = new ResponseEntity<>(mockUser,HttpStatus.OK);
//                Mockito.mock(ResponseEntity.class);
//        Mockito.when(mockResponseEntity.getStatusCode()).thenReturn(HttpStatus.OK);
//        Mockito.when(mockResponseEntity.getBody()).thenReturn(mockUser);
        //var v = new ResponseEntity<>(message, HttpStatus.CREATED);

       // Mockito.when(userService.getUser(Mockito.any(String.class))).thenReturn(mockResponseEntity);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI,id).accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        //String expectedJson = mapper.writeValueAsString(list);
        String outputInJson = result.getResponse().getContentAsString();
//        System.out.println(list);
//        System.out.println(expectedJson);
//        System.out.println(outputInJson);
        assertEquals(HttpStatus.OK.value(),response.getStatus());
    }


    @Test
    public void adduser() throws Exception {

        User mockUser = common_methods.getuser();

//        User mockUser = new User();
//        mockUser.setid("abc");
//        mockUser.setEmail("abc@gmail.com");
//        mockUser.setAddress1("abc,sdk,dsk");
//        mockUser.setAddress2("abc,xcjgd,sdh");
//        mockUser.setMobileNo(7390937904L);
//        mockUser.setFirstname("abcdvr");
//        mockUser.setLastname("dsdsfd");

        String inputInJson = mapper.writeValueAsString(mockUser);
        String message = mockUser + "  added successfully.";
        String URI = "/users";
        Mockito.when(userService.addUser(Mockito.any(User.class))).thenReturn(new ResponseEntity<>(HttpStatus.CREATED));
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URI).accept(MediaType.APPLICATION_JSON).content(inputInJson).contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        //String expectedJson = mapper.writeValueAsString(list);
        String outputInJson = result.getResponse().getContentAsString();
        assertEquals(HttpStatus.CREATED.value(),response.getStatus());

    }

    @Test
    public void updateuser() throws Exception {

        User mockUser = common_methods.getuser();

//        User mockUser = new User();
//        mockUser.setid("abc");
//        mockUser.setEmail("abc@gmail.com");
//        mockUser.setAddress1("abc,sdk,dsk");
//        mockUser.setAddress2("abc,xcjgd,sdh");
//        mockUser.setMobileNo(7390937904L);
//        mockUser.setFirstname("abcdvr");
//        mockUser.setLastname("dsdsfd");

        String inputInJson = mapper.writeValueAsString(mockUser);
        String id = "abc";
        String URI = "/users/{id}";

        Mockito.when(userService.updateUser(Mockito.any(String.class),Mockito.any(User.class))).thenReturn(new ResponseEntity<>(HttpStatus.OK));
        RequestBuilder requestBuilder = MockMvcRequestBuilders.put(URI,id).accept(MediaType.APPLICATION_JSON).content(inputInJson).contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        //String expectedJson = mapper.writeValueAsString(list);
        //String outputInJson = result.getResponse().getContentAsString();
        assertEquals(HttpStatus.OK.value(),response.getStatus());

    }

    @Test
    public void deleteuser() throws Exception {
        String id = "abc";
        String URI = "/users/{id}";
        Mockito.when(userService.deleteUser(Mockito.any(String.class))).thenReturn(new ResponseEntity<>(HttpStatus.OK));
        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete(URI,id);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        //String expectedJson = mapper.writeValueAsString(list);
        //String outputInJson = result.getResponse().getContentAsString();
        assertEquals(HttpStatus.OK.value(),response.getStatus());
    }


}
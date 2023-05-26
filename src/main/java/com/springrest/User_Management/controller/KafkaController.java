//package com.springrest.User_Management.controller;
//
//import com.springrest.User_Management.MessageRequest;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("api/v1/messages")
//public class KafkaController {
//
//    private KafkaTemplate<String,String> kafkaTemplate;
//
//    public KafkaController(KafkaTemplate<String, String> kafkaTemplate) {
//        this.kafkaTemplate = kafkaTemplate;
//    }
//
//    @PostMapping
//    public void publish(@RequestBody MessageRequest request){
//        kafkaTemplate.send("NewTopic1", request.message());
//    }
//
//}

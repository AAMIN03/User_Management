package com.springrest.User_Management;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListeners {

    @KafkaListener(topics = "NewTopic1", groupId = "groupid")
    void listener(String data){
        System.out.println("Listener received: " + data);
    }

}

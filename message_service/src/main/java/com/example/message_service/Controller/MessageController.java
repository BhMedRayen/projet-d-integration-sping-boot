package com.example.message_service.Controller;

import com.example.message_service.Services.MessageServices;
import com.example.message_service.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/messages")
public class    MessageController {
@Autowired
    private MessageServices messageServices;
    @PostMapping("/create/{User_send_id}/{User_recu_id}")
    public ResponseEntity<Message> createMessage(@RequestBody Message message, @PathVariable Long User_send_id,@PathVariable Long User_recu_id){
        Message createMessage = messageServices.createMessage(message,User_send_id,User_recu_id);
        return ResponseEntity.status(HttpStatus.CREATED).body(createMessage);
    }
    @GetMapping("/get/{User_send_id}/{User_recu_id}")
    public ResponseEntity<List<Message>> getMessageBySendId(@PathVariable Long User_send_id,@PathVariable Long User_recu_id){
        List<Message> messages = messageServices.getMessageBySendId(User_send_id,User_recu_id);
        if(!messages.isEmpty()){
            return ResponseEntity.ok(messages);
        }
        else {
            return ResponseEntity.noContent().build();
        }
    }


}

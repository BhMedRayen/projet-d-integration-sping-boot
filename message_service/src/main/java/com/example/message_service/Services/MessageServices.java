package com.example.message_service.Services;

import com.example.message_service.Repository.MessageRepository;
import com.example.message_service.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServices {
@Autowired
    private MessageRepository messageRepository;

    public Message createMessage(Message message,Long User_send_id,Long User_recu_id){
    	if (message != null && User_send_id != null && User_recu_id != null) {
            message.setUser_recu_id(User_recu_id);
            message.setUser_send_id(User_send_id);
            return messageRepository.save(message);
        }else 
    	{
        	return null;
    	}
    	
    }

    public List<Message> getMessageBySendId(Long User_send_id,Long User_recu_id)
    {
        return messageRepository.findbyUserSendId(User_send_id,User_recu_id);
    }
}

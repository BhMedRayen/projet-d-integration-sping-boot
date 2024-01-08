package com.example.message_service.Repository;

import com.example.message_service.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface MessageRepository extends JpaRepository<Message,Long> {
    @Query(value = "select * from message where User_send = ?1 and User_recu = ?2", nativeQuery = true)
    List<Message> findbyUserSendId(Long userSendId, Long userRecuId);
}
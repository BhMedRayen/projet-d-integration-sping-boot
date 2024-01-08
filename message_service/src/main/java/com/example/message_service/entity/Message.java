package com.example.message_service.entity;

import jakarta.persistence.*;
import jdk.jfr.Enabled;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Time;


@Entity @Enabled @Data @NoArgsConstructor @AllArgsConstructor @ToString
@Table(name = "Message")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String message;
    @Column(name = "User_send")
    private Long user_send_id;

    @Column(name = "User_recu")
    private Long user_recu_id;

    private Time time_send;

    @PrePersist
    protected void prePersist(){
        time_send = new Time(System.currentTimeMillis());
    }
    public void setUser_recu_id(Long user_recu_id) {
        this.user_recu_id = user_recu_id;
    }

    public void setUser_send_id(Long user_send_id) {
        this.user_send_id = user_send_id;
    }
}

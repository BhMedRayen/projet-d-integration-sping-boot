package com.example.message_service.entity;

import org.springframework.data.rest.core.config.Projection;

import java.sql.Time;
import java.util.Date;

@Projection(name = "full_message",types = Message.class)
public interface MessageProjection {
    public Long getId();
    public String getMessage();
    public Long getUser_send_id();
    public Long getUser_recu_id();
    public Time getTime_send();
}

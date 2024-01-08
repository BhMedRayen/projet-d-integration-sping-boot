package com.example.freelancer_service.NodeSync;

import net.minidev.json.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@FeignClient(name = "NODE-SERVICE")
public interface NodeSync {
    @RequestMapping(method = RequestMethod.POST, value = "/freelancers/addfreelancer")
    void addFreelancer(@RequestBody JSONObject data);
}

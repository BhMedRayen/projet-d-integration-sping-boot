package com.example.company_service.nodeSync;

import net.minidev.json.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "NODE-SERVICE")
public interface NodeSync {
    @RequestMapping(method = RequestMethod.POST, value = "/companys/addcompany")
    void addCompany(@RequestBody JSONObject data);
}

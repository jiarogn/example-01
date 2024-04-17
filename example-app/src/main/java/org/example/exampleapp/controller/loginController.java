package org.example.exampleapp.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.exampleapp.entity.LoginRequest;
import org.example.exampleapp.entity.Response;
import org.example.exampleapp.service.UseraccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
@Slf4j
public class loginController {
    @Autowired
    private UseraccountService useraccountService;


    @PostMapping("/login")
    public Response<Void> login(@RequestBody LoginRequest loginRequest){
        log.info("用户登录，username={},blockchainID={}",loginRequest.getUsername(),loginRequest.getBlockchainId());
        if(useraccountService.login(loginRequest)){
            return Response.buildSuccess("0","登录成功",null);
        }
        return Response.buildFailure("-1","登录失败");
    }


}

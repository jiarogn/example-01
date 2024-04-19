package org.example.exampleapp.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.exampleapp.entity.AuthData;
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
public class authenticationController {
    @Autowired
    private UseraccountService useraccountService;

    @PostMapping("/submit-transaction")
    public Response<String> submitTransaction(@RequestBody AuthData transactionData){
        log.info("当前用户：{} - {}，请求用户：{} - {}",transactionData.getUserBId(),transactionData.getUserBBlockchain(),transactionData.getUserAId(),transactionData.getUserABlockchain());
        //先查询用户
        if(!useraccountService.search(transactionData.getUserAId(),transactionData.getUserABlockchain())){
            return Response.buildFailure("-1","用户没有合法账户！");
        }
        return null;
    }
}

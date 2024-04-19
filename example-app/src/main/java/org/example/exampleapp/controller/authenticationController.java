package org.example.exampleapp.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.exampleapp.entity.AuthData;
import org.example.exampleapp.entity.Response;
import org.example.exampleapp.service.UseraccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public Response<String> submitTransaction(@RequestBody AuthData authData){
        //先判断账户是否合法
        log.info("发起人：{} - {}，担保人用户：{} - {}",authData.getUserAId(),authData.getUserABlockchain(),authData.getUserBId(),authData.getUserBBlockchain());
        //先查询用户
        if(!useraccountService.search(authData.getUserAId(),authData.getUserBBlockchain())){
            return Response.buildFailure("-1","发起人没有合法跨链账户！");
        }

        //判断是发起人还是担保人
        String role = authData.getRole();
        log.info("当前用户角色：{}",role);
        if ("guarantor".equals(role)) {
            // 处理担保人的逻辑
            guarantorRole(authData);
            // 这里可以调用相应的服务方法来处理担保人的提交
            return Response.buildSuccess("success", "提交成功", null);
        } else if ("initiator".equals(role)) {
            // 处理发起人的逻辑

            // 这里可以调用相应的服务方法来处理发起人的提交
            Response.buildSuccess("success", "提交成功", null);
        }
        return Response.buildFailure("-100","未知用户角色！");
    }

    public Response<String> guarantorRole(AuthData authData){
        log.info("当前用户：{} - {}，请求用户：{} - {}",authData.getUserBId(),authData.getUserBBlockchain(),authData.getUserAId(),authData.getUserABlockchain());
        //1.调用智能合约，Contract(AID,BID)返回AuthID

        //2.重置密码
        String ciphertext = useraccountService.randomPassword(authData);

        return null;
    }

    //3.用户A登录原本的区块链
    // 在原区块链中调用智能合约Contract(AID,BID,AuthID)

}

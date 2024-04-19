package org.example.exampleapp.service;

import org.example.exampleapp.domain.Useraccount;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.exampleapp.entity.LoginRequest;

/**
* @author 123
* @description 针对表【useraccount】的数据库操作Service
* @createDate 2024-04-12 22:02:26
*/
public interface UseraccountService extends IService<Useraccount> {

    boolean login(LoginRequest loginRequest);

    boolean search(String userAId, String userABlockchain);
}

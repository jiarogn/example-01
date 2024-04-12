package org.example.exampleapp.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.exampleapp.domain.Useraccount;
import org.example.exampleapp.service.UseraccountService;
import org.example.exampleapp.mapper.UseraccountMapper;
import org.springframework.stereotype.Service;

/**
* @author 123
* @description 针对表【useraccount】的数据库操作Service实现
* @createDate 2024-04-12 18:27:01
*/
@Service

public class UseraccountServiceImpl extends ServiceImpl<UseraccountMapper, Useraccount>
implements UseraccountService{

}

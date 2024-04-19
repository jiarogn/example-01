package org.example.exampleapp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.exampleapp.domain.Useraccount;
import org.example.exampleapp.entity.LoginRequest;
import org.example.exampleapp.service.UseraccountService;
import org.example.exampleapp.mapper.UseraccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
* @author 123
* @description 针对表【useraccount】的数据库操作Service实现
* @createDate 2024-04-12 22:02:26
*/
@Service
public class UseraccountServiceImpl extends ServiceImpl<UseraccountMapper, Useraccount>
    implements UseraccountService{



    @Override
    public boolean login(LoginRequest loginRequest) {
        String username=loginRequest.getUsername();
        String password=loginRequest.getPassword();
        String blockchainId=loginRequest.getBlockchainId();
        QueryWrapper<Useraccount> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("blockchainID", blockchainId).eq("username", username);
        Useraccount useraccount = baseMapper.selectOne(queryWrapper);
        if (useraccount != null && useraccount.getPassword().equals(password) && useraccount.getStates() == 0) {
            // 更新用户账号的updatetime
            UpdateWrapper<Useraccount> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("blockchainID", blockchainId).eq("username", username);
            updateWrapper.set("update_time", new Date());
            baseMapper.update(null, updateWrapper);
            return true;
        } else {
            return false;
        }
    }

    //检查用户A是否存在
    @Override
    public boolean search(String userAId, String userABlockchain) {
        // 构造查询条件
        QueryWrapper<Useraccount> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", userAId)
                .eq("blockchainID", userABlockchain);
        // 查询数据库中是否存在对应的账户
        int count = baseMapper.selectCount(queryWrapper);
        if(count>0){
            return true;
        }
        return false;
    }
}





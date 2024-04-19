package org.example.exampleapp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.example01.account.Account;
import org.example.exampleapp.domain.Useraccount;
import org.example.exampleapp.entity.AuthData;
import org.example.exampleapp.entity.LoginRequest;
import org.example.exampleapp.service.UseraccountService;
import org.example.exampleapp.mapper.UseraccountMapper;
import org.example.exampleapp.utils.KeyUtils;
import org.example.exampleapp.utils.Sm2Util;
import org.example.exampleapp.utils.Sm3Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;
import java.util.Date;
import java.util.Random;

/**
 * @author 123
 * @description 针对表【useraccount】的数据库操作Service实现
 * @createDate 2024-04-12 22:02:26
 */
@Service
public class UseraccountServiceImpl extends ServiceImpl<UseraccountMapper, Useraccount>
        implements UseraccountService {

    private PublicKey publicKeyA;
    private PublicKey publicKeyB;

    @Override
    public boolean login(LoginRequest loginRequest) {
        String username = loginRequest.getUsername();
        String password = Sm3Util.sm3Hex(loginRequest.getPassword().getBytes());
        String blockchainId = loginRequest.getBlockchainId();
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

    //检查用户是否存在
    @Override
    public boolean search(String userId, String userBlockchain) {
        // 构造查询条件
        QueryWrapper<Useraccount> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", userId)
                .eq("blockchainID", userBlockchain);
        // 查询数据库中是否存在对应的账户
        int count = baseMapper.selectCount(queryWrapper);
        if (count > 0) {
            return true;
        }
        return false;
    }

    @Override
    public String randomPassword(AuthData transactionData) {
        //先查询用户
        // 构造查询条件
        QueryWrapper<Useraccount> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", transactionData.getUserAId())
                .eq("blockchainID", transactionData.getUserBBlockchain()); //这里用的区块链ID是承担人的区块链ID
        // 查询数据库中对应的账户
        Useraccount userA = baseMapper.selectOne(queryWrapper);

        //生成一个随机的六位数作为密码
        // 创建 Random 对象
        Random random = new Random();
        // 生成随机的六位数
        String randomNumber = String.valueOf(random.nextInt(900000) + 100000); // 生成 [100000, 999999] 范围内的随机数
        //重置密码
        String sm3HexValue = Sm3Util.sm3Hex(randomNumber.getBytes());
        userA.setPassword(sm3HexValue);
        baseMapper.updateById(userA);

        //处理公钥
        publicKeyA = KeyUtils.createPublicKey(transactionData.getUserAPublicKey()); //发起人公钥
        publicKeyB = KeyUtils.createPublicKey(transactionData.getUserBPublicKey()); //承担人公钥

        //用A的公钥加密
        byte[] encryptedBytesA = Sm2Util.encrypt(randomNumber.getBytes(), publicKeyA);
        String encryptedTextA = Base64.getEncoder().encodeToString(encryptedBytesA);
        System.out.println("A:" + encryptedTextA);
        //用B的公钥加密
        byte[] encryptedBytesB = Sm2Util.encrypt(encryptedTextA.getBytes(), publicKeyB);
        // 将加密后的字节数组转换为 Base64 字符串
        String encryptedTextB = Base64.getEncoder().encodeToString(encryptedBytesB);
        System.out.println("B:" + encryptedTextB);
        return encryptedTextB;
    }
}





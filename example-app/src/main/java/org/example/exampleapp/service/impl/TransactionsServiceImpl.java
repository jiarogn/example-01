package org.example.exampleapp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.example01.GetTransaction1;
import org.example.exampleapp.chain2.GetTransactionsTest;
import org.example.exampleapp.domain.Transactions;
import org.example.exampleapp.entity.TransactionsApp;
import org.example.exampleapp.service.TransactionsService;
import org.example.exampleapp.mapper.TransactionsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
* @author 123
* @description 针对表【transactions】的数据库操作Service实现
* @createDate 2024-04-13 01:16:33
*/
@Service
public class TransactionsServiceImpl extends ServiceImpl<TransactionsMapper, Transactions>
    implements TransactionsService{

    @Autowired
    private TransactionsMapper transactionsMapper;

    @Override
    public Page<Transactions> getTransactions(String blockchainId, int pageNum, int pageSize) {
        //清空当前数据库
        transactionsMapper.delete(null);
        List<TransactionsApp> transactionsExample=new ArrayList<>();
        List<org.example.example01.Transactions1> transactionsExample1=new ArrayList<>();

        //根据blockchainid来选择查询哪个区块链中的数据
        switch (blockchainId){
            case "1":
                // 创建一个 GetTransaction 对象
                GetTransaction1 getTransaction1 = new GetTransaction1();
                // 调用 getAllTransaction() 方法获取交易列表
                transactionsExample1 = getTransaction1.getAllTransaction1();
                // 遍历 transactionsExample1 列表，进行转换并添加到 transactionsExample 中
                for (org.example.example01.Transactions1 transaction1 : transactionsExample1) {
                    TransactionsApp transactionApp = new TransactionsApp(
                            transaction1.getTransHash(),
                            transaction1.getFrom(),
                            transaction1.getTo(),
                            transaction1.getGas()
                    );
                    transactionsExample.add(transactionApp);
                }
                break;
            case "2":
                // 创建一个 GetTransaction 对象
                GetTransactionsTest getTransactionsTest=new GetTransactionsTest();
                // 调用 getAllTransaction() 方法获取交易列表
                System.out.println("初始化成功？");
                break;
            default:
                return null;
        }
        // 使用流将原始列表转换为新的列表
        List<Transactions> transactionsApp = transactionsExample.stream()
                .map(transactionExample -> {
                    Transactions transactionApp = new Transactions();
                    transactionApp.setTransHash(transactionExample.getTransHash());
                    transactionApp.setFromBlock(transactionExample.getFrom());
                    transactionApp.setToBlock(transactionExample.getTo());
                    transactionApp.setGas(transactionExample.getGas());
                    return transactionApp;
                })
                .collect(Collectors.toList());

        // 使用 Lambda 表达式将交易信息逐个存入数据库
        transactionsApp.forEach(transaction -> {
            Transactions entity = new Transactions();
            entity.setTransHash(transaction.getTransHash());
            entity.setFromBlock(transaction.getFromBlock());
            entity.setToBlock(transaction.getToBlock());
            entity.setGas(transaction.getGas());
            transactionsMapper.insert(entity);
        });

          return transactionsMapper.selectPage(new Page<>(pageNum,pageSize),null);
    }


}





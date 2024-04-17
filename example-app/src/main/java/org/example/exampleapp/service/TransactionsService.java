package org.example.exampleapp.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.example.exampleapp.domain.Transactions;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 123
* @description 针对表【transactions】的数据库操作Service
* @createDate 2024-04-13 01:16:33
*/
public interface TransactionsService extends IService<Transactions> {


    Page<Transactions> getTransactions(String blockchainId, int pageNum, int pageSize);
}

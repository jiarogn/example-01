package org.example.exampleapp.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.example.exampleapp.domain.Transactions;
import org.example.exampleapp.service.TransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
@Slf4j
@Controller
public class tradesController {
    @Autowired
    private TransactionsService transactionsService;

    @GetMapping("/transactions")
    public Page<Transactions> getTransactions(
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "10") int pageSize) {
        log.info("分页查询请求：pageNum:{},pageSize:{}",pageNum,pageSize);
        String blockchainId="1";
        Page<Transactions> pages=transactionsService.getTransactions(blockchainId,pageNum, pageSize);
        return pages;
    }
}

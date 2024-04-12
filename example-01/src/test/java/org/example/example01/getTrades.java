package org.example.example01;

import org.example.example01.account.Account;
import org.fisco.bcos.sdk.BcosSDK;
import org.fisco.bcos.sdk.client.Client;
import org.fisco.bcos.sdk.client.protocol.response.BcosTransactionReceiptsDecoder;
import org.fisco.bcos.sdk.client.protocol.response.BcosTransactionReceiptsInfo;
import org.fisco.bcos.sdk.client.protocol.response.BlockNumber;
import org.fisco.bcos.sdk.model.TransactionReceipt;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigInteger;
import java.util.List;

@SpringBootTest
public class getTrades {
    // 获取配置文件路径
    String configFile="src/main/resources/static/config-example.toml";
    // 初始化BcosSDK
    BcosSDK sdk =  BcosSDK.build(configFile);
    // 为群组1初始化client
    Client client = sdk.getClient(Integer.valueOf(1));
   @Test
    void contextLoads() {
        System.out.println(client.getGroupId());
        // 获取群组1的块高
        BlockNumber blockNumber = client.getBlockNumber();
        System.out.println(blockNumber);
    }

    @Test
    void CreateAccount(){
        String path="src/main/resources/conf/0xa5254f2b6050d199618b1ce482e60ee156c9342c.pem";
        Account account=new Account();
        account.loadPemAccount(client,path);
    }

    @Test
    void QueryAllTransactions(){
        System.out.println("--------------------------------------------");
        System.out.println(client.getBlockNumber());
        System.out.println("--------------------------------------------");
    }

    //查询所有交易
    @Test
    void SendTransaction(){
        // 获取最新的区块高度
        BlockNumber blockNumber = client.getBlockNumber();
        BigInteger latestBlockNumber = blockNumber.getBlockNumber();
        int i=latestBlockNumber.intValue();
        while (i>0){
            // 获取交易回执
            BcosTransactionReceiptsDecoder bcosTransactionReceiptsDecoder = client.getBatchReceiptsByBlockNumberAndRange(BigInteger.valueOf(i), "0", "-1");
            // 解码交易回执信息
            BcosTransactionReceiptsInfo.TransactionReceiptsInfo receiptsInfo = bcosTransactionReceiptsDecoder.decodeTransactionReceiptsInfo();
            // 获取交易回执列表
            List<TransactionReceipt> receiptList = receiptsInfo.getTransactionReceipts();
            System.out.println(i);
            System.out.println("-------------------------------------------------------------");
            // 遍历交易回执列表，打印交易信息
            for (TransactionReceipt receipt : receiptList) {
                String transactionHash = receipt.getTransactionHash();
                String from = receipt.getFrom();
                String to = receipt.getTo();
                String gasUsed = receipt.getGasUsed();
                // 其他交易详情...
                System.out.println("Transaction Hash: " + transactionHash);
                System.out.println("From: " + from);
                System.out.println("To: " + to);
                System.out.println("Gas Used: " + gasUsed);
                // 打印其他交易详情...
            }
            System.out.println("-------------------------------------------------------------");
            i--;
        }
    }

}

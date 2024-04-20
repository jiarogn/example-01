package org.example.example01;

import org.fisco.bcos.sdk.BcosSDK;
import org.fisco.bcos.sdk.client.Client;
import org.fisco.bcos.sdk.client.protocol.response.BcosTransactionReceiptsDecoder;
import org.fisco.bcos.sdk.client.protocol.response.BcosTransactionReceiptsInfo;
import org.fisco.bcos.sdk.client.protocol.response.BlockNumber;
import org.fisco.bcos.sdk.model.TransactionReceipt;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;


import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GetTransaction1 {
    String configFile = "D:\\学习\\大四下\\毕业设计\\FISCO_BCOS\\example-parent\\example-01\\src\\main\\resources\\static\\config-example.toml";
    // 初始化BcosSDK
    BcosSDK sdk = BcosSDK.build(configFile);
    // 为群组1初始化client
    Client client = sdk.getClient(Integer.valueOf(1));
    public List<Transactions1> getAllTransaction1() {
        List<Transactions1> transactions=new ArrayList<>();

        // 获取最新的区块高度
        BlockNumber blockNumber = client.getBlockNumber();
        BigInteger latestBlockNumber = blockNumber.getBlockNumber();
        int i = latestBlockNumber.intValue();
        while (i > 0) {
            System.out.println("-------------------------------------------------------------");
            // 获取交易回执
            BcosTransactionReceiptsDecoder bcosTransactionReceiptsDecoder = client.getBatchReceiptsByBlockNumberAndRange(BigInteger.valueOf(i), "0", "-1");
            // 解码交易回执信息
            BcosTransactionReceiptsInfo.TransactionReceiptsInfo receiptsInfo = bcosTransactionReceiptsDecoder.decodeTransactionReceiptsInfo();
            // 获取交易回执列表
            List<TransactionReceipt> receiptList = receiptsInfo.getTransactionReceipts();
            // 遍历交易回执列表，存入list中
            // 使用流和Lambda表达式将交易回执列表转换为 Transaction 对象列表，然后添加到现有的 transactions 列表中
            transactions.addAll(receiptList.stream()
                    .map(receipt -> new Transactions1(receipt.getTransactionHash(), receipt.getFrom(), receipt.getTo(), receipt.getGasUsed()))
                    .collect(Collectors.toList()));
            System.out.println(i);

            i--;
        }
        return transactions;

    }
}
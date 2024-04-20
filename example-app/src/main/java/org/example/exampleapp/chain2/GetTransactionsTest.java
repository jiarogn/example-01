package org.example.exampleapp.chain2;

import org.fisco.bcos.sdk.BcosSDK;
import org.fisco.bcos.sdk.client.Client;

public class GetTransactionsTest {
    String configFile="D:\\学习\\大四下\\毕业设计\\FISCO_BCOS\\example-parent\\example-app\\src\\main\\resources\\static\\config-example.toml";
    // 初始化BcosSDK
    BcosSDK sdk =  BcosSDK.build(configFile);
    // 为群组1初始化client
    Client client = sdk.getClient(Integer.valueOf(1));
}

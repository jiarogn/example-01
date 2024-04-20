package org.example.example02;

import org.fisco.bcos.sdk.BcosSDK;
import org.fisco.bcos.sdk.client.Client;

public class GetTransaction2 {
    String configFile="D:\\学习\\大四下\\毕业设计\\FISCO_BCOS\\example-parent\\example-02\\src\\main\\resources\\static\\config-example.toml";
    // 初始化BcosSDK
    BcosSDK sdk =  BcosSDK.build(configFile);
    // 为群组1初始化client
    Client client = sdk.getClient(Integer.valueOf(1));
}

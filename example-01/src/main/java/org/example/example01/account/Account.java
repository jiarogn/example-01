package org.example.example01.account;


import org.fisco.bcos.sdk.crypto.CryptoSuite;
import org.fisco.bcos.sdk.crypto.keypair.CryptoKeyPair;
import org.fisco.bcos.sdk.crypto.keystore.KeyTool;
import org.fisco.bcos.sdk.crypto.keystore.PEMKeyStore;
import org.fisco.bcos.sdk.model.CryptoType;
import org.fisco.bcos.sdk.client.Client;

public class Account {
    // 创建非国密类型的CryptoSuite
    CryptoSuite cryptoSuite = new CryptoSuite(CryptoType.ECDSA_TYPE);
    // 随机生成非国密公私钥对
    CryptoKeyPair cryptoKeyPair = cryptoSuite.createKeyPair();
    // 获取账户地址
    String accountAddress = cryptoKeyPair.getAddress();

    // 将随机生成的账户信息保存在pemFilePath指定的路径
    public void saveAccountWithPem(CryptoKeyPair cryptoKeyPair, String pemFilePath)
    {
        // 以pem的格式保存账户文件到pemFilePath路径
        cryptoKeyPair.storeKeyPairWithPem(pemFilePath);
    }

    // 将随机生成的账户信息保存在账户配置${keyStoreDir}指定的目录下
    public void saveAccountWithPemToKeyStoreDir(CryptoKeyPair cryptoKeyPair)
    {
        // 账户文件名为${accountAddress}.pem
        cryptoKeyPair.storeKeyPairWithPemFormat();
    }

    //从指定文件加载pem文件示例如下：
    public KeyTool loadPem(String pemFilePath)
    {
        return new PEMKeyStore(pemFilePath);
    }

    // 从pemAccountFilePath指定路径加载pem账户文件，并将其设置为交易发送账户
    public void loadPemAccount(Client client, String pemAccountFilePath)
    {
        // 通过client获取CryptoSuite对象
        CryptoSuite cryptoSuite = client.getCryptoSuite();
        // 加载pem账户文件
        cryptoSuite.loadAccount("pem", pemAccountFilePath, null);
        System.out.println(cryptoSuite);
    }

}

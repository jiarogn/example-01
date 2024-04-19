package org.example.exampleapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthData {
    private String userAId; //用户A的id
    private String userABlockchain; //用户A所在区块链
    private String userAPublicKey; //用户A公钥
    //感觉这里我应该直接从网页中读取用户的登录信息，但没时间了，先凑合着来吧，能用就行
    private String userBId; //用户B的id
    private String userBBlockchain; //用户B所在区块链
    private String userBPublicKey; //用户B公钥
}

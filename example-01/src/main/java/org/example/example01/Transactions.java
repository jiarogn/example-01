package org.example.example01;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transactions{
    /**
     * 交易的哈希值
     */
    private String transHash;

    /**
     * 交易发起方
     */
    private String from;

    /**
     * 交易接收方
     */
    private String to;

    /**
     * 交易消耗gas
     */
    private String gas;


}
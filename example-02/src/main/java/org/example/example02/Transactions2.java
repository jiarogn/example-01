package org.example.example02;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transactions2 {
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
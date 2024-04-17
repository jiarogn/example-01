package org.example.exampleapp.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @TableName transactions
 */
@TableName(value ="transactions")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transactions implements Serializable {
    /**
     * 交易的哈希值
     */
    private String transHash;

    /**
     * 交易发起方
     */
    private String fromBlock;

    /**
     * 交易接收方
     */
    private String toBlock;

    /**
     * 交易消耗gas
     */
    private String gas;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Transactions other = (Transactions) that;
        return (this.getTransHash() == null ? other.getTransHash() == null : this.getTransHash().equals(other.getTransHash()))
            && (this.getFromBlock() == null ? other.getFromBlock() == null : this.getFromBlock().equals(other.getFromBlock()))
            && (this.getToBlock() == null ? other.getToBlock() == null : this.getToBlock().equals(other.getToBlock()))
            && (this.getGas() == null ? other.getGas() == null : this.getGas().equals(other.getGas()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getTransHash() == null) ? 0 : getTransHash().hashCode());
        result = prime * result + ((getFromBlock() == null) ? 0 : getFromBlock().hashCode());
        result = prime * result + ((getToBlock() == null) ? 0 : getToBlock().hashCode());
        result = prime * result + ((getGas() == null) ? 0 : getGas().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", transHash=").append(transHash);
        sb.append(", fromBlock=").append(fromBlock);
        sb.append(", toBlock=").append(toBlock);
        sb.append(", gas=").append(gas);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
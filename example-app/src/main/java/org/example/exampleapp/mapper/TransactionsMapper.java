package org.example.exampleapp.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.exampleapp.domain.Transactions;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author 123
* @description 针对表【transactions】的数据库操作Mapper
* @createDate 2024-04-13 01:16:33
* @Entity org.example.exampleapp.domain.Transactions
*/
@Mapper
public interface TransactionsMapper extends BaseMapper<Transactions> {

}





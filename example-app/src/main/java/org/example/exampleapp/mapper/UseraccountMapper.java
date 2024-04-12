package org.example.exampleapp.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.exampleapp.domain.Useraccount;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author 123
* @description 针对表【useraccount】的数据库操作Mapper
* @createDate 2024-04-12 22:02:26
* @Entity org.example.exampleapp.domain.Useraccount
*/
@Mapper
public interface UseraccountMapper extends BaseMapper<Useraccount> {

}





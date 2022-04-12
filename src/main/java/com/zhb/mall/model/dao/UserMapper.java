package com.zhb.mall.model.dao;

import com.zhb.mall.model.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;


public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User selectByName(String name);

    /*当只有一个入参无需注解，当有多个入参需要@Param注解*/
    User selectLogin(@Param("userName") String userName, @Param("password") String password);
}
package com.zhb.mall.model.dao;

import com.zhb.mall.model.pojo.Cart;
import org.springframework.stereotype.Repository;


public interface CartMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Cart record);

    int insertSelective(Cart record);

    Cart selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Cart record);

    int updateByPrimaryKey(Cart record);
}
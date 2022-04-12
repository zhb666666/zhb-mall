package com.zhb.mall.model.dao;

import com.zhb.mall.model.pojo.Order;
import org.springframework.stereotype.Repository;


public interface OrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);
}
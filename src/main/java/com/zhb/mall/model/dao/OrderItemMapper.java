package com.zhb.mall.model.dao;

import com.zhb.mall.model.pojo.OrderItem;
import org.springframework.stereotype.Repository;


public interface OrderItemMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderItem record);

    int insertSelective(OrderItem record);

    OrderItem selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderItem record);

    int updateByPrimaryKey(OrderItem record);
}
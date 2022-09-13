package com.zhb.mall.model.dao;

import com.zhb.mall.model.pojo.OrderItem;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;


public interface OrderItemMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderItem record);

    int insertSelective(OrderItem record);

    OrderItem selectByPrimaryKey(Integer id);

    ArrayList<OrderItem> selectByOrderNo(String orderNo);

    int updateByPrimaryKeySelective(OrderItem record);

    int updateByPrimaryKey(OrderItem record);
}
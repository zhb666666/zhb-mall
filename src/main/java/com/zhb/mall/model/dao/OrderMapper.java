package com.zhb.mall.model.dao;

import com.zhb.mall.model.pojo.Order;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;


public interface OrderMapper {
    int deleteByPrimaryKey(Integer id);

    Order selectByOrderNo(String orderNo);

    ArrayList<String> select();

    ArrayList<String> selectByUserId(Integer userId);
    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Integer id);


    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);
}
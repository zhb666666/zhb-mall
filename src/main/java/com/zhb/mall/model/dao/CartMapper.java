package com.zhb.mall.model.dao;

import com.zhb.mall.model.pojo.Cart;
import com.zhb.mall.model.vo.CartVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;


public interface CartMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Cart record);

    int insertSelective(Cart record);

    Cart selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Cart record);

    int updateByPrimaryKey(Cart record);

    Cart selectIdAndUser(@Param("userId") Integer userId,@Param("productId") Integer productId);

    ArrayList<CartVO> selectByUserId(@Param("userId") Integer userId);
}
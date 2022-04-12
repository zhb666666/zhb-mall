package com.zhb.mall.model.dao;

import com.github.pagehelper.PageInfo;
import com.zhb.mall.model.pojo.Product;
import com.zhb.mall.model.request.ProductListReq;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


public interface ProductMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);

    Product selectByName(String name);

    int batchUpdateSellStatus(@Param("ids") Integer[] ids,@Param("sellStatus")  Integer sellStatus);

    ArrayList<Product> selectList();

    ArrayList<Product> list(ProductListReq productListReq);

}
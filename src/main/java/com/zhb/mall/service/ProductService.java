package com.zhb.mall.service;


import com.github.pagehelper.PageInfo;
import com.zhb.mall.model.pojo.Product;
import com.zhb.mall.model.request.AddProductReq;
import com.zhb.mall.model.request.ProductListReq;
import com.zhb.mall.model.request.UpdateProductReq;

import java.util.ArrayList;

public interface ProductService {


    void add(AddProductReq addProductReq);

    void update(UpdateProductReq updateProductReq);

    void delete(Integer id);

    void batchUpdateSellStatus(Integer[] ids, Integer sellStatus);

    PageInfo productList(Integer pageNum, Integer pageSize);

    Product detail(Integer id);

    PageInfo list(ProductListReq productListReq);



}

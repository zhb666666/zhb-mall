package com.zhb.mall.controller;


import com.github.pagehelper.PageInfo;
import com.zhb.mall.common.ApiRestResponse;
import com.zhb.mall.model.pojo.Product;
import com.zhb.mall.model.request.ProductListReq;
import com.zhb.mall.service.ProductService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
@Autowired
ProductService productService;

    @ApiOperation("前台商品详情")
    @GetMapping("/product/detail")
    public ApiRestResponse detail(@RequestParam Integer id){
        Product product = productService.detail(id);
        if(product==null)return ApiRestResponse.error(2001,"查询无果");
        else return ApiRestResponse.success(product);
    }

    @GetMapping("/product/list")
    @ApiOperation("前台商品列表")
    public ApiRestResponse list(ProductListReq productListReq){

        PageInfo list = productService.list(productListReq);
       return ApiRestResponse.success(list);

    }

}

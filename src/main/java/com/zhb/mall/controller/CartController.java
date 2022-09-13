package com.zhb.mall.controller;

import com.zhb.mall.common.ApiRestResponse;
import com.zhb.mall.filter.UserFilter;

import com.zhb.mall.model.vo.CartVO;
import com.zhb.mall.service.CartService;
import com.zhb.mall.service.CategoryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * 描述：购物车控制器
 * 标注：其实userId可以直接放在service中
 */
@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    CartService cartService;

    @GetMapping("/list")
    public ApiRestResponse list(){

        return ApiRestResponse.success(cartService.list());
    }
    @PostMapping("/add")
    public ApiRestResponse add(@RequestParam Integer productId, @RequestParam Integer count){
        ArrayList<CartVO> add = cartService.add(UserFilter.currentUser.getId(),productId, count);
        return ApiRestResponse.success(add);
    }
    @PostMapping("/update")
    public ApiRestResponse update(@RequestParam Integer productId, @RequestParam Integer count){
        ArrayList<CartVO> update = cartService.update(UserFilter.currentUser.getId(),productId, count);
        return ApiRestResponse.success(update);
    }
    @PostMapping("/delete")
    public ApiRestResponse delete(@RequestParam Integer productId){
        ArrayList<CartVO> delete = cartService.delete(UserFilter.currentUser.getId(), productId);
        return ApiRestResponse.success(delete);
    }
    @PostMapping("/select")
    public ApiRestResponse select(@RequestParam Integer productId,@RequestParam Integer selected){
        ArrayList<CartVO> select = cartService.select(UserFilter.currentUser.getId(), productId, selected);
        return ApiRestResponse.success(select);
    }
    @PostMapping("/selectAll")
    public ApiRestResponse selectAll(Integer selected){
        ArrayList<CartVO> select = cartService.selectAll(UserFilter.currentUser.getId(),selected);
        return ApiRestResponse.success(select);
    }
}

package com.zhb.mall.controller;

import com.github.pagehelper.PageInfo;
import com.zhb.mall.common.ApiRestResponse;
import com.zhb.mall.common.Constant;
import com.zhb.mall.exception.zhbMallExceptionEnum;
import com.zhb.mall.model.pojo.User;
import com.zhb.mall.model.request.AddCategoryRequest;
import com.zhb.mall.model.request.UpdateCategoryRequest;
import com.zhb.mall.model.vo.CategoryVO;
import com.zhb.mall.service.CategoryService;
import com.zhb.mall.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;


@Controller
public class CategoryController {
    @Autowired
    UserService userService;

    @Autowired
    CategoryService categoryService;

    /**
     * 管理员后台管理，增加目录分类
     *
     * @param "session"
     * @param addCategoryRequest
     * @return
     */
    @ApiOperation("后台添加目录")
    @PostMapping("/admin/category/add")
    @ResponseBody
    public ApiRestResponse addCategory(/*HttpSession session,*/ @Valid @RequestBody AddCategoryRequest addCategoryRequest) {
        /*四个参数都不能为空(已用@Valid代替)
        if(addCategoryRequest.getName()==null||addCategoryRequest.getOrderNum()==null||addCategoryRequest.getParentId()==null||addCategoryRequest.getType()==null){
            return ApiRestResponse.error(zhbMallExceptionEnum.PARA_NOT_NULL);
        }*/
       /* User user = (User) session.getAttribute(Constant.ZHB_MALL_USER);
        //如果未登陆要提示先登陆
        if (user == null) {
            return ApiRestResponse.error(zhbMallExceptionEnum.NEED_LOGIN);
        }
        //如果不是管理员提示无权限
        if (userService.checkAdminRole(user)) {
            categoryService.add(addCategoryRequest);
            return ApiRestResponse.success();

        } else return ApiRestResponse.error(zhbMallExceptionEnum.NEED_ADMIN);*/
        //用过滤器代替上述登陆和管理员校验
        categoryService.add(addCategoryRequest);
        return ApiRestResponse.success();
    }

    @ApiOperation("后台更新目录")
    @PostMapping("/admin/category/update")
    @ResponseBody
    public ApiRestResponse updateCategory(/*HttpSession session,*/ @RequestBody @Valid UpdateCategoryRequest updateCategoryRequest) {
        /*User user = (User) session.getAttribute(Constant.ZHB_MALL_USER);
        //如果未登陆要提示先登陆
        if (user == null) {
            return ApiRestResponse.error(zhbMallExceptionEnum.NEED_LOGIN);
        }
        //如果不是管理员提示无权限
        if (userService.checkAdminRole(user)) {
            categoryService.update(updateCategoryRequest);
            return ApiRestResponse.success();

        } else return ApiRestResponse.error(zhbMallExceptionEnum.NEED_ADMIN);*/
        //用过滤器代替上述登陆和管理员校验
        categoryService.update(updateCategoryRequest);
        return ApiRestResponse.success();

    }
    @ApiOperation("后台删除目录")
    @PostMapping("/admin/category/delete")
    @ResponseBody
    public ApiRestResponse deleteCategory(Integer id) {

        categoryService.delete(id);
        return ApiRestResponse.success();

    }

    @ApiOperation("后台目录列表")
    @GetMapping("/admin/category/list")
    @ResponseBody
    public ApiRestResponse listCategoryForAdmin(@RequestParam("pageNum") Integer pageNum,@RequestParam("pageSize") Integer pageSize) {

        PageInfo pageInfo = categoryService.listForAdmin(pageNum, pageSize);

        return ApiRestResponse.success(pageInfo);

    }

    @ApiOperation("前台台目录列表")
    @GetMapping("/category/list")
    @ResponseBody
    public ApiRestResponse  listCategoryForCustomer() {

        List<CategoryVO> categoryVOS = categoryService.listCategoryForCustomer(0);
        return ApiRestResponse.success(categoryVOS);

    }
}

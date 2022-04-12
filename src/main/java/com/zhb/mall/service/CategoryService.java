package com.zhb.mall.service;

import com.github.pagehelper.PageInfo;
import com.zhb.mall.model.request.AddCategoryRequest;
import com.zhb.mall.model.request.UpdateCategoryRequest;
import com.zhb.mall.model.vo.CategoryVO;

import java.util.List;

public interface CategoryService {


    void add(AddCategoryRequest addCategoryRequest);

    void update(UpdateCategoryRequest updateCategoryRequest);

    void delete(Integer id);

    PageInfo listForAdmin(Integer pageNum, Integer pageSize);

    List<CategoryVO> listCategoryForCustomer(Integer parentId);

}

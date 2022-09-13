package com.zhb.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhb.mall.exception.zhbMallException;
import com.zhb.mall.exception.zhbMallExceptionEnum;
import com.zhb.mall.model.dao.CategoryMapper;
import com.zhb.mall.model.pojo.Category;
import com.zhb.mall.model.request.AddCategoryRequest;
import com.zhb.mall.model.request.UpdateCategoryRequest;
import com.zhb.mall.model.vo.CategoryVO;
import com.zhb.mall.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;


@Service
public class CategoryServiceImpl implements CategoryService {

    
    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public void add(AddCategoryRequest addCategoryRequest) {
        Category category = new Category();
        //将addCategoryRequest的参数值赋给与之对应category的参数
        BeanUtils.copyProperties(addCategoryRequest, category);
        Category category1 = categoryMapper.selectName(category.getName());
        if (category1 != null) {
            throw new zhbMallException(zhbMallExceptionEnum.NAME_EXISTED);
        }
        int insert = categoryMapper.insertSelective(category);
        if (insert == 0) throw new zhbMallException(zhbMallExceptionEnum.CREATE_FAILED);

    }

    @Override
    public void update(UpdateCategoryRequest updateCategoryRequest) {
        System.out.println(updateCategoryRequest);
        if (updateCategoryRequest.getName() != null) {
            Category category = categoryMapper.selectName(updateCategoryRequest.getName());
            boolean b = category.getId().equals(updateCategoryRequest.getId());
            if ((category != null) && (!b)) {
                throw new zhbMallException(zhbMallExceptionEnum.NAME_EXISTED);
            }
        }
        Category category = new Category();
        BeanUtils.copyProperties(updateCategoryRequest, category);
        int i = categoryMapper.updateByPrimaryKeySelective(category);
        if (i == 0) {
            throw new zhbMallException(zhbMallExceptionEnum.UPDATE_FAILED);
        }
    }

    @Override
    public void delete(Integer id) {
        if (id == null) {
            throw new zhbMallException(zhbMallExceptionEnum.PARA_NOT_NULL);
        }
        int i = categoryMapper.deleteByPrimaryKey(id);
        if (i != 1) {
            throw new zhbMallException(zhbMallExceptionEnum.DELETE_FAILED);
        }

    }

    @Override
    public PageInfo listForAdmin(Integer pageNum, Integer pageSize) {
        //PageHelper.startPage相当于开启分页,通过拦截MySQL的方式,把你的查询语句拦截下来加limit.
        PageHelper.startPage(pageNum, pageSize, "type,order_num");
        List<Category> categoryList = categoryMapper.selectList();
        PageInfo pageInfo = new PageInfo(categoryList);
        return pageInfo;

    }

    @Override
    @Cacheable(value = "listCategoryForCustomer")//摄制redis的key值
    public List<CategoryVO> listCategoryForCustomer(Integer parentId) {
        ArrayList<CategoryVO> categoryVOList = new ArrayList<>();
        recursivelyFindCategories(categoryVOList, parentId);
        return categoryVOList;
    }



public void recursivelyFindCategories(List<CategoryVO> categoryVOList, Integer parentId) {
        List<Category> categoryList = categoryMapper.selectCategoriesByParentId(parentId);
        if (!CollectionUtils.isEmpty(categoryList)) {
            for (int i = 0; i < categoryList.size(); i++) {
                Category category = categoryList.get(i);
                CategoryVO categoryVO = new CategoryVO();
                System.out.println(category);
                BeanUtils.copyProperties(category, categoryVO);
                categoryVOList.add(categoryVO);
                recursivelyFindCategories(categoryVO.getChildCategory(), categoryVO.getId());

            }
        }
    }
}

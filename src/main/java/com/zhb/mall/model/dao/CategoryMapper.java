package com.zhb.mall.model.dao;

import com.zhb.mall.model.pojo.Category;
import com.zhb.mall.model.vo.CategoryVO;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface CategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Category record);

    int insertSelective(Category record);

    Category selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);

    Category selectName(String name);

    List<Category> selectList();

    List<Category> selectCategoriesByParentId(Integer parentId);
}
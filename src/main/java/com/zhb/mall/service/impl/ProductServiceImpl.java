package com.zhb.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhb.mall.exception.zhbMallException;
import com.zhb.mall.exception.zhbMallExceptionEnum;
import com.zhb.mall.model.dao.ProductMapper;
import com.zhb.mall.model.pojo.Product;
import com.zhb.mall.model.request.AddProductReq;
import com.zhb.mall.model.request.ProductListReq;
import com.zhb.mall.model.request.UpdateProductReq;
import com.zhb.mall.model.vo.CategoryVO;
import com.zhb.mall.service.CategoryService;
import com.zhb.mall.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：   商品服务实现类
 */
@Service
public class ProductServiceImpl implements ProductService {


    @Autowired
    ProductMapper productMapper;

    @Autowired
    CategoryServiceImpl categoryService;

    @Override
    public void add(AddProductReq addProductReq) {
        Product product = new Product();
        BeanUtils.copyProperties(addProductReq, product);
        Product product1 = productMapper.selectByName(product.getName());
        if (product1 != null) {
            throw new zhbMallException(zhbMallExceptionEnum.NAME_EXISTED);
        }
        int i = productMapper.insertSelective(product);
        if (i == 0) {
            throw new zhbMallException(zhbMallExceptionEnum.INSERT_FAILED);
        }

    }

    @Override
    public void update(UpdateProductReq updateProductReq) {
        Product product = new Product();
        BeanUtils.copyProperties(updateProductReq, product);
        Product product1 = productMapper.selectByName(product.getName());
        if (product1 != null) {
            throw new zhbMallException(zhbMallExceptionEnum.NAME_EXISTED);
        }
        int i = productMapper.updateByPrimaryKeySelective(product);
        if (i == 0) {
            throw new zhbMallException(zhbMallExceptionEnum.UPDATE_FAILED);
        }
    }

    @Override
    public void delete(Integer id) {
        int i = productMapper.deleteByPrimaryKey(id);
        if (i == 0) {
            throw new zhbMallException(zhbMallExceptionEnum.DELETE_FAILED);
        }
    }

    @Override
    public void batchUpdateSellStatus(Integer[] ids, Integer sellStatus) {
        productMapper.batchUpdateSellStatus(ids, sellStatus);
    }

    @Override
    public PageInfo productList(Integer pageNum, Integer pageSize) {

        PageHelper.startPage(pageNum, pageSize, "update_time desc");
        ArrayList<Product> products = productMapper.selectList();
        return new PageInfo(products);
    }

    @Override
    public Product detail(Integer id) {
        Product product = productMapper.selectByPrimaryKey(id);
        return product;
    }

    @Override
    public PageInfo list(ProductListReq productListReq) {

        PageHelper.startPage(productListReq.getPageNum(), productListReq.getPageSize());
        String keyword = productListReq.getKeyword();
        if (keyword != null) productListReq.setKeyword("%" + keyword + "%");
        else productListReq.setKeyword("");
        ArrayList<CategoryVO> categoryVOS=new ArrayList<>() ;
        System.out.println(productListReq.getCategoryId());
         categoryService.recursivelyFindCategories(categoryVOS,productListReq.getCategoryId());

        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(productListReq.getCategoryId());
        if (categoryVOS != null) {

            for (CategoryVO categoryVO : categoryVOS) {
                arrayList.add(categoryVO.getId());
            }
        }
        productListReq.setIds(arrayList);
        PageHelper.startPage(productListReq.getPageNum(), productListReq.getPageSize(),productListReq.getOrderBy());

        ArrayList<Product> list = productMapper.list(productListReq);

        return new PageInfo(list);
    }

}

package com.zhb.mall.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhb.mall.common.ApiRestResponse;
import com.zhb.mall.common.Constant;
import com.zhb.mall.exception.zhbMallException;
import com.zhb.mall.exception.zhbMallExceptionEnum;
import com.zhb.mall.model.pojo.Category;
import com.zhb.mall.model.pojo.Product;
import com.zhb.mall.model.request.AddProductReq;
import com.zhb.mall.model.request.UpdateProductReq;
import com.zhb.mall.service.ProductService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.swing.plaf.multi.MultiMenuItemUI;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 描述：后台商品管理Controller
 */
@RestController//相当于给每个方法加@ResponseBody
public class ProductAdminController {

    @Autowired
    ProductService productService;


    @ApiOperation("后台商品增加")
    @PostMapping("/admin/product/add")
    public ApiRestResponse addProduct(@Valid @RequestBody AddProductReq addProductReq) {
        productService.add(addProductReq);
        return ApiRestResponse.success();
    }


    @ApiOperation("后台上传图片")
    @PostMapping("/admin/upload/file")//postman里面的from-data的参数可用@RequestParam接收
    public ApiRestResponse upload(HttpServletRequest httpServletRequest, @RequestParam("file") MultipartFile file) {

        String Filename = file.getOriginalFilename();
        String subFileName = Filename.substring(Filename.lastIndexOf("."));
        UUID uuid = UUID.randomUUID();
        String newFileName = uuid + subFileName;
        File fileContent = new File(Constant.FILE_UPLOAD_DIR);
        File destFile = new File(fileContent +  newFileName);
        //File destFile = new File(fileContent + "\\" + newFileName);
        //部署到云服务器File destFile = new File(fileContent +  newFileName);
        if (!fileContent.exists()) {
            if (!fileContent.mkdirs()) {
                throw new zhbMallException(zhbMallExceptionEnum.MKDIR_FAILED);
            }
        }
        try {
            file.transferTo(destFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            return ApiRestResponse
                    .success(getHost(new URI(httpServletRequest.getRequestURL() + "")) + "/images/"
                            + newFileName);
        } catch (URISyntaxException e) {
            return ApiRestResponse.error(zhbMallExceptionEnum.UPLOAD_FAILED);
        }

    }


    private URI getHost(URI uri) {
        URI effectiveURI;
        try {
            effectiveURI = new URI(uri.getScheme(), uri.getUserInfo(), uri.getHost(), uri.getPort(),
                    null, null, null);
        } catch (URISyntaxException e) {
            effectiveURI = null;
        }
        return effectiveURI;
    }

    @PostMapping("/admin/product/update")
    @ApiOperation("后台更新商品")
    public ApiRestResponse updateProduct(@Valid @RequestBody UpdateProductReq updateProductReq) {
        productService.update(updateProductReq);
        return ApiRestResponse.success();
    }

    @PostMapping("/admin/product/delete")
    @ApiOperation("后台删除商品")
    public ApiRestResponse deleteProduct(Integer id) {
        productService.delete(id);
        return ApiRestResponse.success();
    }

    @PostMapping("/admin/product/updatesellstatus")
    @ApiOperation("批量上下架商品")
    public ApiRestResponse batchUpdateSellStatus(@RequestParam Integer[] ids, @RequestParam Integer sellStatus) {
        productService.batchUpdateSellStatus(ids, sellStatus);
        return ApiRestResponse.success();

    }

    @GetMapping("/admin/product/list")
    @ApiOperation("后台商品")
    public ApiRestResponse productList(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {

        PageHelper.startPage(pageNum, pageSize, "update_time desc");

        PageInfo pageInfo = productService.productList(pageNum, pageSize);

        return ApiRestResponse.success(pageInfo);
    }

}

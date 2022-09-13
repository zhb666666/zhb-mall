package com.zhb.mall.common;

import com.zhb.mall.exception.zhbMallException;
import com.zhb.mall.exception.zhbMallExceptionEnum;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Constant {
    public static final String SALT = "lijiayue+zhonghongbing=1314521";
    public static final String ZHB_MALL_USER = "zhb_mall_user";
    //@Value("${file.upload.dir}")不能直接加这，需要加在下面的set方法上让spring自动注入
    public static String FILE_UPLOAD_DIR;

    @Value("${file.upload.dir}")
    public void setFileUploadDir(String fileUploadDir) {
        FILE_UPLOAD_DIR = fileUploadDir;
    }

    public interface SaleStatus {

        int NOT_SALE = 0;//商品下架状态
        int SALE = 1;//商品上架状态
    }

    public interface Cart {

        int UN_CHECKED = 0;//购物车未选中状态
        int CHECKED = 1;//购物车选中状态
    }


    public enum orderStatus {
        CANCELLED(0, "用户已取消"),
        UNPAID(10, "未付款"),
        PAID(20, "已付款"),
        SHIPPED(30, "已发货"),
        Finish(40, "交易完成");

        orderStatus(int code, String value) {
            this.value = value;
            this.code = code;
        }

        private String value;
        private int code;

        public static orderStatus codeOf(int code) {
            for (orderStatus orderStatus : values()) {
                if(code==orderStatus.getCode())return orderStatus;
            }
            throw new zhbMallException(zhbMallExceptionEnum.NO_ENUM);
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }


    }
}

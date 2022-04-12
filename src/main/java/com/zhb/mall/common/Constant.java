package com.zhb.mall.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Constant {
    public static final String SALT="lijiayue+zhonghongbing=1314521";
    public static final String ZHB_MALL_USER="zhb_mall_user";
    //@Value("${file.upload.dir}")不能直接加这，需要加在下面的get方法上让spring自动注入
    public static String FILE_UPLOAD_DIR;

    @Value("${file.upload.dir}")
    public void setFileUploadDir(String fileUploadDir) {
        FILE_UPLOAD_DIR = fileUploadDir;
    }

}

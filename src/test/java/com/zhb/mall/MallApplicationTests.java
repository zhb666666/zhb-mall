package com.zhb.mall;

import com.zhb.mall.common.Constant;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@SpringBootTest
class MallApplicationTests {

    @Test
    void contextLoads()  {
        System.out.println("hello");
    }
    @Test
    void test()  {
        File fileContent = new File(Constant.FILE_UPLOAD_DIR);
        System.out.println(fileContent);
        System.out.println(fileContent+"\\"+"aaaaa.jpg");
    }

}

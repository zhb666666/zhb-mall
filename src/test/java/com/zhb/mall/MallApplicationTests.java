package com.zhb.mall;

import com.zhb.mall.common.Constant;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;

import static java.sql.DriverManager.println;
import static org.apache.coyote.http11.Constants.a;
import static org.apache.ibatis.javassist.bytecode.SyntheticAttribute.tag;

@SpringBootTest
class MallApplicationTests {

    @Test
    void contextLoads()  {
        System.out.println(a);
    }
    @Test
    void test()  {
        File fileContent = new File(Constant.FILE_UPLOAD_DIR);
        System.out.println(fileContent);
        System.out.println(fileContent+"\\"+"aaaaa.jpg");
    }
@Test
    public void test2(){

    System.out.println(new Date());
}
}

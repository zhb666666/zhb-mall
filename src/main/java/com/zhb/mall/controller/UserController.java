package com.zhb.mall.controller;

import com.mysql.cj.util.StringUtils;
import com.zhb.mall.common.ApiRestResponse;
import com.zhb.mall.common.Constant;
import com.zhb.mall.exception.zhbMallException;
import com.zhb.mall.exception.zhbMallExceptionEnum;
import com.zhb.mall.model.pojo.User;
import com.zhb.mall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/test")
    @ResponseBody//将java对象转换成json对象
    public User personalPage() {
        return userService.getUser();
    }

    /**
     * 用户注册接口
     * @param userName
     * @param password
     * @return
     * @throws zhbMallException
     */
    @PostMapping("/register")
    @ResponseBody
    public ApiRestResponse register(@RequestParam("userName") String userName, @RequestParam("password") String password) throws zhbMallException {
        if (StringUtils.isNullOrEmpty(userName)) {//username!=null
            return ApiRestResponse.error(zhbMallExceptionEnum.NEED_USER_NAME);
        }
        if (StringUtils.isNullOrEmpty(password)) {
            return ApiRestResponse.error(zhbMallExceptionEnum.NEED_PASSWORD);
        }
        if (password.length() < 8) {
            return ApiRestResponse.error(zhbMallExceptionEnum.PASSWORD_TOO_SHORT);
        }
        userService.register(userName, password);
        return ApiRestResponse.success();
    }

    /**
     * 用户登录接口
     * @param userName
     * @param password
     * @param session
     * @return
     */
    @PostMapping("/login")
    @ResponseBody
    public ApiRestResponse login(@RequestParam("userName") String userName, @RequestParam("password") String password, HttpSession session) {
        if (StringUtils.isNullOrEmpty(userName)) {//username!=null
            return ApiRestResponse.error(zhbMallExceptionEnum.NEED_USER_NAME);
        }
        if (StringUtils.isNullOrEmpty(password)) {
            return ApiRestResponse.error(zhbMallExceptionEnum.NEED_PASSWORD);
        }
        if (password.length() < 8) {
            return ApiRestResponse.error(zhbMallExceptionEnum.PASSWORD_TOO_SHORT);
        }
        User user = userService.login(userName, password);
        user.setPassword(null);//保存用户信息时不保存密码
        session.setAttribute(Constant.ZHB_MALL_USER, user);//使用session保存信息
        return ApiRestResponse.success(user);
    }

    /**
     * 用户更新个性签名接口
     * @param session
     * @param signature
     * @return
     */
    @PostMapping("/user/update")
    @ResponseBody
    //浏览器发送请求的请求头中保函HttpSession
    public ApiRestResponse updateUserInfo(HttpSession session, @RequestParam String signature) {
        //Object attribute = session.getAttribute(Constant.ZHB_MALL_USER);我们知道他是User所以给他转换类型
        User currentUser = (User) session.getAttribute(Constant.ZHB_MALL_USER);
        if (currentUser == null) {
            return ApiRestResponse.error(zhbMallExceptionEnum.NEED_LOGIN);
        }
        User user = new User();
        user.setId(currentUser.getId());
        user.setPersonalizedSignature(signature);
        userService.updateInformation(user);
        return ApiRestResponse.success();

    }

    /**
     *用户退出登陆接口
     * @param session
     * @return
     */
    @PostMapping("/user/logout")
    @ResponseBody
    public ApiRestResponse logout(HttpSession session){
        session.removeAttribute(Constant.ZHB_MALL_USER);
        return ApiRestResponse.success();
    }

    /**
     * 管理员登陆接口
     * @param userName
     * @param password
     * @param session
     * @return
     */
    @PostMapping("/adminLogin")
    @ResponseBody
    public ApiRestResponse adminLogin(@RequestParam("userName") String userName, @RequestParam("password") String password, HttpSession session) {
        if (StringUtils.isNullOrEmpty(userName)) {//username!=null
            return ApiRestResponse.error(zhbMallExceptionEnum.NEED_USER_NAME);
        }
        if (StringUtils.isNullOrEmpty(password)) {
            return ApiRestResponse.error(zhbMallExceptionEnum.NEED_PASSWORD);
        }
        if (password.length() < 8) {
            return ApiRestResponse.error(zhbMallExceptionEnum.PASSWORD_TOO_SHORT);
        }
        User user = userService.login(userName, password);
        if (userService.checkAdminRole(user)) {
            user.setPassword(null);//保存用户信息时不保存密码
            session.setAttribute(Constant.ZHB_MALL_USER, user);//使用session保存信息
            return ApiRestResponse.success(user);
        }else {
            return  ApiRestResponse.error(zhbMallExceptionEnum.NEED_ADMIN);
        }
    }

}


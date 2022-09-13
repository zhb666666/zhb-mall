package com.zhb.mall.service.impl;

import com.zhb.mall.common.Constant;
import com.zhb.mall.exception.zhbMallException;
import com.zhb.mall.exception.zhbMallExceptionEnum;
import com.zhb.mall.model.dao.UserMapper;
import com.zhb.mall.model.pojo.User;
import com.zhb.mall.service.UserService;
import com.zhb.mall.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;



    @Override
    public void register(String userName, String password) throws zhbMallException {
        User user = userMapper.selectByName(userName);
        if (user != null) {

            throw new zhbMallException(zhbMallExceptionEnum.NAME_EXISTED);
        }

        User user1 = new User();
        user1.setUsername(userName);
        try {
            user1.setPassword(MD5Utils.getMD5Str(password));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        int i = userMapper.insertSelective(user1);
        if (i == 0) {
            throw new zhbMallException(zhbMallExceptionEnum.INSERT_FAILED);
        }

    }

    @Override
    public User login(String userName, String password) throws zhbMallException {
        String md5Password = null;
        try {
            md5Password = MD5Utils.getMD5Str(password);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        User user = userMapper.selectLogin(userName, md5Password);
        if (user == null) {
            throw new zhbMallException(zhbMallExceptionEnum.WRONG_PASSWORD);
        }
        return user;
    }

    @Override
    public void updateInformation(User user) {
        //更新个性签名
        int updateCount = userMapper.updateByPrimaryKeySelective(user);
        if (updateCount > 1) {
            throw new zhbMallException(zhbMallExceptionEnum.UPDATE_FAILED);
        }

    }

    @Override
    public boolean checkAdminRole(User user) {
        return user.getRole().equals(2);
    }

}

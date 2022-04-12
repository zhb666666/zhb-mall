package com.zhb.mall.service;

import com.zhb.mall.exception.zhbMallException;
import com.zhb.mall.model.pojo.User;

public interface UserService {

    public User getUser();

    public void register(String userName,String password)throws zhbMallException;


    User login(String userName, String password) throws zhbMallException;

    void updateInformation(User user);

    boolean checkAdminRole(User user);
}
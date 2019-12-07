package com.cex.tmall.service;

import com.cex.tmall.pojo.User;

public interface UserService  extends BaseService{
    boolean isExist(String name);
    User get(String name,String password);
}

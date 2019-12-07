package com.cex.tmall.service.impl;

import com.cex.tmall.pojo.User;
import com.cex.tmall.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends BaseServiceImpl implements UserService {
    @Override
    public boolean isExist(String name) {
        List l = list("name",name);
        if(!l.isEmpty()) return true;
        return false;
    }

    @Override
    public User get(String name, String password) {
        List u = list("name",name,"password",password);
        if(u.isEmpty()){
            return null;
        }
        return (User) u.get(0);
    }
}

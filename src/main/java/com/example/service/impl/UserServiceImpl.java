package com.example.service.impl;

import com.example.dao.mapper.UserMapper;
import com.example.entity.User;
import com.example.entity.UserExample;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserServiceImpl {

    @Autowired
    private UserMapper userMapper;

    public List<User> getUsers(Integer userId){
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        if(!"".equals(userId == null ? "" : userId)){
            criteria.andIdEqualTo(userId);
        }
        return userMapper.selectByExample(example);
    }
}

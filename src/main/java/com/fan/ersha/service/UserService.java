package com.fan.ersha.service;

import com.fan.ersha.entity.User;
import com.fan.ersha.mapper.UserMapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: 范
 * @description:
 * @date: Created in 17:42 2018/1/25
 * @modifiedBy: 修改人
 */
@Service(value = "userService")
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public List<User> findAllUser(int pageNum, int pageSize) {
        //将参数传给这个方法就可以实现物理分页了，非常简单。
        PageHelper.startPage(pageNum, pageSize);
        return userMapper.selectAllUser();
    }

}

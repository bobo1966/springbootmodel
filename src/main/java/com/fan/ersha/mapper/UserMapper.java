package com.fan.ersha.mapper;

import com.fan.ersha.entity.User;

import java.util.List;

/**
 * @author: 范
 * @description: UserMapper
 * @date: Created in 17:54 2018/1/25
 * @modifiedBy: 修改人
 */
public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    /**
     * @Author: fan
     * @Description: 查询所有数据
     * @Date: 10:34 2018/1/26
     */
    List<User> selectAllUser();
}
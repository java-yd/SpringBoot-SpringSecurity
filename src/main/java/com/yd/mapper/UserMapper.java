package com.yd.mapper;

import org.apache.ibatis.annotations.Param;

import com.yd.entity.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    /**
     * 根据账号密码或密码查找用户，
     * @param username
     * @param password
     * @return
     */
	User selectByUsernameOrPassword(@Param("username")String username,@Param("password") String password);
}
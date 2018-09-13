package com.qty.dao;

import org.apache.ibatis.annotations.Mapper;

import com.qty.entity.UserInfo;


@Mapper
public interface UserInfoMapper {
	
	/*
	 * 根据条件查询用户信息
	 */
	public UserInfo getUserInfo(UserInfo userInfo);

}

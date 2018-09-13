/**
 * 
 */
package com.qty.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qty.dao.UserInfoMapper;
import com.qty.entity.UserInfo;
import com.qty.service.UserInfoService;

/**
 * @author qty
 *
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {
	
	@Autowired
	private UserInfoMapper userInfoMapper;

	@Override
	public UserInfo doLogin(UserInfo userInfo) {
		return userInfoMapper.getUserInfo(userInfo);
	}

}

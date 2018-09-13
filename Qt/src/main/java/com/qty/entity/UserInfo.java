/**
 * 
 */
package com.qty.entity;

/**
 * @author qty
 *
 */
public class UserInfo {
	private Integer userId;
	private String userName;
	private String userPw;
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPw() {
		return userPw;
	}
	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}
	@Override
	public String toString() {
		return "UserInfo [userId=" + userId + ", userName=" + userName + ", userPw=" + userPw + "]";
	}
	
}

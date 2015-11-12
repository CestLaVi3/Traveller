package com.chinasoft.service.login;

import com.chinasoft.model.user.User;

public interface LoginService {
	User findByLognName(String loginName);
	/**
	 * 
	 * @param user
	 */
	void insertUser(User user);
}

package com.chinasoft.dao.login;

import com.chinasoft.model.user.User;

public interface LoginDao {
	User login(String loginName);
	
	void insertUser(User user);
}

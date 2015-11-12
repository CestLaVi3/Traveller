package com.chinasoft.dao.login;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.chinasoft.dao.BaseDao;
import com.chinasoft.model.user.User;

public class LoginDaoImpl extends BaseDao implements LoginDao {

	public User login(String loginName) {
		System.out.println("dao");
		String sql="SELECT * FROM SYS_USER WHERE LOGIN_NAME=?";
		
		return (User)getJdbcTemplate().query(sql, new Object[] {loginName},new ResultSetExtractor() {

			public Object extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				if (rs.next()) {
					User user=new User();
					user.setBirthday(rs.getDate("BIRTHDAY"));
					user.setAddress(rs.getString("ADDRESS"));
					user.setEmail(rs.getString("EMAIL"));
					user.setImageUrl(rs.getString("IMAGE_URL"));
					user.setLocation(rs.getString("LOCATION"));
					user.setLoginName(rs.getString("LOGIN_NAME"));
					user.setPassword(rs.getString("PASSWORD"));
					user.setPetName(rs.getString("PETNAME"));
					user.setPhoneNum(rs.getString("PHONE_NUM"));
					user.setQqNum(rs.getString("QQ_NUM"));
					user.setSex(rs.getString("SEX"));
					user.setUserId(rs.getLong("USER_ID"));
					user.setUserName(rs.getString("USER_NAME"));
					return user;
				}
				return null;
			}
		});
	}

	public void insertUser(User user) {
		String sql = "insert into sys_user(user_id,login_name,email,password) values (seq_user.nextval,?,?,?)";
		this.getJdbcTemplate().update(sql, new Object[] {user.getEmail(),user.getEmail(),user.getPassword()});
	}

}

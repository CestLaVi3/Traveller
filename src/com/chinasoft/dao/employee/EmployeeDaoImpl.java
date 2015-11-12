package com.chinasoft.dao.employee;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.chinasoft.dao.BaseDao;
import com.chinasoft.model.employee.Employee;
public class EmployeeDaoImpl extends BaseDao implements EmployeeDao {

	public Employee findById(int id) {
		String sql = "select * from employee where id = ? ";
		return (Employee) getJdbcTemplate().query(sql, new Object[] {id}, new ResultSetExtractor() {
			public Object extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				if (rs.next()) {
					Employee em = new Employee();
					em.setBirthday(rs.getDate("birthday"));
					em.setId(rs.getInt("id"));
					em.setName(rs.getString("name"));
					em.setResume(BaseDao.clobToString(rs.getClob("resume")));
					em.setSalary(rs.getFloat("salary"));
					return em;
				}
				return null;
			}
		});
	}
	

	

}

package com.chinasoft.service.employee;

import org.springframework.dao.DataAccessException;

import com.chinasoft.common.utils.Exception.BusinessException;
import com.chinasoft.common.utils.log.HollyinfoLog;
import com.chinasoft.common.utils.log.HollyinfoLogger;
import com.chinasoft.controller.BaseJsonController;
import com.chinasoft.dao.employee.EmployeeDao;
import com.chinasoft.model.employee.Employee;


public class EmployeeServiceImpl implements EmployeeService{
	private static final HollyinfoLog LOG = HollyinfoLogger.getLog(EmployeeServiceImpl.class);
	private EmployeeDao employeeDao;
	public EmployeeDao getEmployeeDao() {
		return employeeDao;
	}
	public void setEmployeeDao(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}
	public Employee findById(int id) {
		try {
			return employeeDao.findById(id);
		} catch (DataAccessException e) {
			LOG.debug("错误信息", e);
			throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
		}
	}
	
}

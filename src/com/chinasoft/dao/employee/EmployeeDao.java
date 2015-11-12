package com.chinasoft.dao.employee;
import com.chinasoft.model.employee.*;
import com.chinasoft.model.performance.Model;
import com.chinasoft.model.user.SysUser;

import java.util.*;
public interface EmployeeDao {

	Employee findById(int id);
}

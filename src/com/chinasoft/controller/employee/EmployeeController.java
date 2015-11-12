package com.chinasoft.controller.employee;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.chinasoft.common.utils.Exception.BusinessException;
import com.chinasoft.controller.BaseJsonController;
import com.chinasoft.model.employee.Employee;
import com.chinasoft.service.employee.EmployeeService;

@Controller
public class EmployeeController extends BaseJsonController {
	private EmployeeService employeeService;
	@RequestMapping("/employeeController/findById.do")
	public String findById(HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject req = getJsonObjectReq(request);
		int id = req.getInt("id");
		try {
			Employee em = employeeService.findById(id);
			sendForObject(em, response,null);
		} catch (BusinessException e) {
			sendForError(e, response);
		}
		return null;
	}
	public EmployeeService getEmployeeService() {
		return employeeService;
	}
	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
}

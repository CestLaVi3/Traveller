package com.chinasoft.model.employee;
import java.sql.Clob;
import java.util.*;
import com.chinasoft.model.*;
import com.chinasoft.model.position.Position;
import com.chinasoft.model.performance.*;

public class Employee {
	private Integer id;
	private String name;
	private Float salary;
	private Date birthday;
	private String resume;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Float getSalary() {
		return salary;
	}
	public void setSalary(Float salary) {
		this.salary = salary;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getResume() {
		return resume;
	}
	public void setResume(String resume) {
		this.resume = resume;
	}
}

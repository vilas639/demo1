package com.example.demo.dto;

import java.io.Serializable;

public class Employee  {

	private Long Empid;
	
	private String Name;
	
	
	public Employee()
	{
		
	}


	public Employee(Long empid, String name) {
		super();
		Empid = empid;
		Name = name;
	}


	public Long getEmpid() {
		return Empid;
	}


	public void setEmpid(Long empid) {
		Empid = empid;
	}


	public String getName() {
		return Name;
	}


	public void setName(String name) {
		Name = name;
	}


	@Override
	public String toString() {
		return "Employee{" +
                "empId=" + Empid +
                ", name='" + Name + '\'' +
                '}';
	}
	
	
	
	
}

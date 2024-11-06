package com.example.demo.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.demo.dto.Employee;
import com.example.demo.service.FirnServices;

public class Finfallback  implements FirnServices{

	@Override
	public ResponseEntity<List<Employee>> getemployeemicro(Employee employee, String channel) {
		// TODO Auto-generated method stub
		List<Employee> e1= new ArrayList<Employee>();
		Employee e= new Employee();
		e.setEmpid(1l);
		e.setName("error");
		
		e1.add(e);
		return new ResponseEntity<List<Employee>>(e1, HttpStatus.SERVICE_UNAVAILABLE);
	}

}

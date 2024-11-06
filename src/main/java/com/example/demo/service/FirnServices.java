package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.example.demo.dto.Employee;
import com.example.demo.exception.Finfallback;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@FeignClient(name="DEMO1",url ="http://192.168.1.101:8082/" )
public interface FirnServices {

	
	
	@PostMapping("test2/listofemp")
	 @CircuitBreaker(name = "externalServiceCircuitBreaker", fallbackMethod = "getEmployeeFallback")
	public ResponseEntity<List<Employee>> getemployeemicro(@RequestBody Employee employee,@RequestHeader("channel") String channel);
	
	
	
	default String fallbackMethod1(Employee id, Throwable throwable) {
        return "Fallback response for resource " + id;
    }
	
	default ResponseEntity<List<Employee>> getEmployeeFallback(Employee employee, String channel, Throwable throwable) {
        List<Employee> fallbackEmployees = new ArrayList<>();
        Employee fallbackEmployee = new Employee();
        
        fallbackEmployee.setEmpid(-1L);
        fallbackEmployee.setName("Service is unavailable - returning fallback data");
        
        fallbackEmployees.add(fallbackEmployee);
        
        return new ResponseEntity<>(fallbackEmployees, HttpStatus.SERVICE_UNAVAILABLE);
    }
}

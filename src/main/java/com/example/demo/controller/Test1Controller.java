package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.demo.dto.Employee;
import com.example.demo.service.FirnServices;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("test/")
public class Test1Controller {

    @Autowired	
   RestTemplate resttemplate;
    
    
    @Autowired
	 FirnServices   finserv;
	
    Test1Controller(FirnServices   finserv1)
	{
		this.finserv=finserv1;
	}
	
	
	//get empoyee object
	@PostMapping("getemp")
	public ResponseEntity<Employee> getemployee(@RequestBody Employee employee)
	{
	   System.out.println("employee"+employee);
	   
	  
	   return new ResponseEntity<Employee>(employee, HttpStatus.ACCEPTED);
	}
	
	@PostMapping("getempmicro")
	public ResponseEntity<Employee> getemployeemicro(@RequestBody Employee employee) throws JsonProcessingException
	{
		
	//		curl --location 'http://127.0.0.1:8082/test2/getemp1' \
	//		--header 'channel: web' \
	//		--data '{
	//		    "empid": 1,
	//		    "name": "vilas"
	//		}
	//		'
			
	//		{
	//			  "name": "rajesh",
	//			  "empid": 77
	//			}		
		System.out.println("employee"+employee);
		Employee responseBody=null;
        
        
        try {
            RestTemplate restTemplate = new RestTemplate();
           // String url = "http://127.0.0.1:8082/test2/getemp1";
           String url = "http://DEMO2/test2/getemp1";

            // Set up headers
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("channel", "web");

            // Create the Employee object
            

            // Convert Employee object to JSON string
//            ObjectMapper mapper = new ObjectMapper();
//            String requestBody = mapper.writeValueAsString(employee);

            // Wrap the headers and JSON body into an HttpEntity
            HttpEntity<Employee> requestEntity = new HttpEntity<>(employee, headers);

            // Send POST request
            ResponseEntity<Employee> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, Employee.class);

            // Print the response body
             responseBody = response.getBody();

            // Print the Employee object
            if (responseBody != null) {
                System.out.println("Response: " + responseBody);
            } else {
                System.out.println("Response body is null.");
            }
            
           
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return new ResponseEntity<Employee>(employee, HttpStatus.ACCEPTED);
	}

	   
	  
        
        @PostMapping("getemplistmicro")
    	public ResponseEntity<List<Employee>> getemployeelistmicro(@RequestBody Employee employee) throws JsonProcessingException
    	{
    		
//        	curl --location 'http://127.0.0.1:8082/test2/listofemp' \
//        	--header 'channel: web' \
//        	--data '{
//        	        "empid": 1,
//        	            "name": "vilas"
//        	            }
//        	'
    	//		'
    			
//        	[
//        	  {
//        	    "name": "vilas0",
//        	    "empid": 0
//        	  },
//        	  {
//        	    "name": "vilas1",
//        	    "empid": 1
//        	  },
//        	  {
//        	    "name": "vilas2",
//        	    "empid": 2
//        	  },
//        	  {
//        	    "name": "vilas3",
//        	    "empid": 3
//        	  },
//        	  {
//        	    "name": "vilas4",
//        	    "empid": 4
//        	  },
//        	  {
//        	    "name": "vilas5",
//        	    "empid": 5
//        	  },
//        	  {
//        	    "name": "vilas6",
//        	    "empid": 6
//        	  },
//        	  {
//        	    "name": "vilas7",
//        	    "empid": 7
//        	  },
//        	  {
//        	    "name": "vilas8",
//        	    "empid": 8
//        	  },
//        	  {
//        	    "name": "vilas9",
//        	    "empid": 9
//        	  }
//        	]
    		System.out.println("employee"+employee);
    	   List<Employee> responseBody1=null;
            
            
            try {
                RestTemplate restTemplate = new RestTemplate();
                String url = "http://127.0.0.1:8082/test2/listofemp";

                // Set up headers
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                headers.set("channel", "web");

                // Create the Employee object
                System.out.println(employee.toString());

                // Convert Employee object to JSON string
//                ObjectMapper mapper = new ObjectMapper();
//                String requestBody = mapper.writeValueAsString(employee);

                // Wrap the headers and JSON body into an HttpEntity
                HttpEntity<Employee> requestEntity = new HttpEntity<>(employee, headers);

                // Send POST request
                ResponseEntity<List<Employee>> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, new ParameterizedTypeReference<List<Employee>>() {});

                // Print the response body
                 responseBody1 =  response.getBody();

                // Print the Employee object
                if (responseBody1 != null) {
                    System.out.println("Response: " + responseBody1);
                } else {
                    System.out.println("Response body is null.");
                }
                
               
            } catch (Exception e) {
                e.printStackTrace();
            }

	 
	  
	  
            return new ResponseEntity<List<Employee>>(responseBody1, HttpStatus.ACCEPTED);
            }
        
        
        @PostMapping("getempmicrofin")
    	public ResponseEntity<List<Employee>> getemployeemicrofin(@RequestBody Employee employee,@RequestHeader String channel) throws JsonProcessingException
    	{
        	
        	
        	return finserv.getemployeemicro(employee, channel);
    	}
	
	@GetMapping("info")
	public String getinfo()
	{
		String s="demo";
		 //call other service
		   System.out.println("call other service start");
		   s= resttemplate.getForObject("http://127.0.0.1:8082/test2/info1",String.class);
		  
		  System.out.println("call other service end"+s);
		return s;
	}
	
	//address details
}

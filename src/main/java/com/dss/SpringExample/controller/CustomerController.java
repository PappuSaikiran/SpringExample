package com.dss.SpringExample.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dss.SpringExample.entity.Customer;
import com.dss.SpringExample.entity.ListDtoClass;
import com.dss.SpringExample.entity.ProjectDetails;
import com.dss.SpringExample.service.CustomerService;

@RestController
public class CustomerController {

	@Autowired
	CustomerService customerservice;

	@Autowired
	ProjectDetails projectDetails;

	/*
	 * @Autowired ListDtoClass listDto;
	 */

	@GetMapping(value = "test")
	public String getProjectName() {
		return projectDetails.getProjectname();

	}

	@GetMapping(value = "test1")
	public String getProjectDescription() {
		return projectDetails.getProjectdescription();

	}

	@GetMapping(value = "test2")
	public String getName() {
		return projectDetails.getName();

	}
	
	@GetMapping("/test3")
	public String getDetails() {
		return "Spring Boot Example";
	}

	/*
	 * @Value("${app.title}") private String appTitle;
	 * 
	 * @Value("${app.description}") private String appDescription;
	 * 
	 * @GetMapping("value") public String getValue() { return appTitle; }
	 */

	@PostMapping(value = "getCustomers")
	public ResponseEntity<Map<String, Object>> getAllCustomers(@RequestBody ListDtoClass request) {
		HashMap<String, Object> res = new HashMap<String, Object>();
		Map<String, Object> data = customerservice.getAllCustomers(request);
		res.put("data", data.get("data"));
		res.put("total", data.get("total"));
		res.put("success", true);
		return new ResponseEntity<Map<String, Object>>(res, HttpStatus.OK);

	}

	@PostMapping(value = "addCustomer")
	public ResponseEntity<Map<String, Object>> saveCustomer(@Valid @RequestBody Customer customer) {
		boolean success = true;
		Map<String, Object> res = new HashMap<String, Object>();
		Map<String, Object> data = customerservice.sveCustomer(customer);
		if (data.containsKey("message")) {
			res.put("message", data.get("message"));
			success = false;
		} else {
			res.put("data", customerservice.getCustomerDetailsById((int) data.get("id")));
		}
		res.put("success", success);
		return new ResponseEntity<Map<String, Object>>(res, HttpStatus.OK);
	}

	@PutMapping(value = "updateCustomer")
	public ResponseEntity<Map<String, Object>> updateCustomerDetails(@RequestBody Customer customer) {
		HashMap<String, Object> res = new HashMap<String, Object>();
		res.put("data", customerservice.updateCustomerDetails(customer));
		res.put("success", true);
		return new ResponseEntity<Map<String, Object>>(res, HttpStatus.OK);
	}

	@DeleteMapping(value = "deleteById/{id}")
	public ResponseEntity<Map<String, Object>> deleteCustomerDetails(@PathVariable int id) {
		HashMap<String, Object> res = new HashMap<String, Object>();
		res.put("data", customerservice.deleteCustomerDetails(id));
		res.put("success", true);
		return new ResponseEntity<Map<String, Object>>(res, HttpStatus.OK);
	}

	@GetMapping(value = "getCustomer/{id}")
	public ResponseEntity<Map<String, Object>> getCustomerDetailsById(@PathVariable int id) {
		Map<String, Object> res = new HashMap<String, Object>();
		Map<String, Object> returnRes = customerservice.getCustomerDetailsById(id);
		if (returnRes.size() == 0) {
			res.put("message", "the customer with id " + id + " not found");
		} else {
			res.put("data", returnRes);
		}
		res.put("success", true);
		return new ResponseEntity<Map<String, Object>>(res, HttpStatus.OK);
	}
}

package com.dss.SpringExample.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dss.SpringExample.dao.CustomerDao;
import com.dss.SpringExample.entity.Customer;
import com.dss.SpringExample.entity.ListDtoClass;

@Service
public class CustomerService {
	
	@Autowired
	CustomerDao customerDao;

	public Map<String, Object> getAllCustomers(ListDtoClass request) {
		return customerDao.getAllCustomers(request);
	}

	public Map<String, Object> sveCustomer(Customer customer) {
		Map<String, Object> res=new HashMap<String, Object>();
		int id=0;
		if(validateCustomerCity(customer.getCustomer_city()+"",id)) {
			id=customerDao.saveCustomer(customer);
			res.put("id",id );
		}else {
			res.put("message", "the customer city already exists ");
		}
		return res;
	}
	public boolean validateCustomerCity(String city,int id) {
		return customerDao.validateCustomerCity(city, id);
	}

	public int updateCustomerDetails(Customer customer) {
		return customerDao.updateCustomerDetails(customer);
	}

	public int deleteCustomerDetails(int id) {
		return customerDao.deleteCustomerDetails(id);
	}

	public  Map<String, Object> getCustomerDetailsById(int id) {
		return customerDao.getCustomerDetailsById(id);
	}

}

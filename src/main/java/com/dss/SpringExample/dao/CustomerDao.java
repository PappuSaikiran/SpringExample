package com.dss.SpringExample.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import com.dss.SpringExample.entity.Customer;
import com.dss.SpringExample.entity.ListDtoClass;
@Repository
public class CustomerDao {
	
	@Autowired
	JdbcTemplate jdbctemplate;

	@SuppressWarnings("unchecked")
	public Map<String, Object> getAllCustomers(@RequestBody ListDtoClass request) {
	    String query="SELECT SQL_CALC_FOUND_ROWS * FROM `customer` where 1=1 $condition$ ORDER BY $condition1$ LIMIT $start$,$end$";
		Map<String, Object> res=new HashMap<String, Object>();
		String condition="";
		HashMap<String, Object>filterdata=(HashMap<String, Object>) request.getFilter();
		for(Map.Entry<String, Object> entry:filterdata.entrySet()) {
			condition+="AND "+entry.getKey() +" LIKE '%"+entry.getValue()+"%'";
		}
		String condition1="";
		HashMap<String, Object>sortData=(HashMap<String, Object>) request.getSort();
		for(Map.Entry<String, Object> entryData:sortData.entrySet()) {
			condition1+=entryData.getKey() +" "+entryData.getValue()+" ,";
		}
		int rows=(int) request.getRows();
		int pages=(int) request.getPages();
		int start=rows*(pages-1);
		int end=rows;
		query=query.replace("$start$", start+"");
		query=query.replace("$end$", end+"");
		query=query.replace("$condition$", condition);
		condition1=condition1.substring(0,condition1.length()-1);
		query=query.replace("$condition1$", condition1);
		List<Map<String, Object>> data=jdbctemplate.queryForList(query);
		res.put("data", data);
		long total= (long)jdbctemplate.queryForMap("SELECT FOUND_ROWS() total").get("total");
		res.put("total", total);
		System.out.println(query);
		return res;
	}

	public int saveCustomer(Customer customer) {
		int id=getGeneratedId("INSERT INTO `customer`(customer_name,customer_city,payment_amt,postalcode) values(?,?,?,?)",customer.getCustomer_name(),customer.getCustomer_city(),customer.getPayment_amt(),customer.getPostalcode());
		return id;
	}
	
	public int getGeneratedId(String query,Object...args) {
		KeyHolder holder=new GeneratedKeyHolder();
		jdbctemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
				for (int i = 0; i < args.length; i++) {
					ps.setObject(i + 1, args[i]);
				}
				return ps;
			}
		}, holder);
		int id = holder.getKey().intValue();
		return id;
	}

	public int updateCustomerDetails(Customer customer) {
		return jdbctemplate.update("UPDATE `customer` SET customer_name=?, customer_city=? WHERE customer_id=?",customer.getCustomer_name(),customer.getCustomer_city(),customer.getCustomer_id());
	}

	public int deleteCustomerDetails(int id) {
		return jdbctemplate.update("DELETE FROM `customer` WHERE customer_id=?",id);
	}

	public Map<String, Object> getCustomerDetailsById(int id) {
		Map<String, Object> res=new HashMap<String, Object>();
		try {
			res=jdbctemplate.queryForMap("SELECT * FROM `customer` WHERE customer_id=?",id);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res ;
	}
	public boolean validateCustomerCity(String city,int id) {
		Map<String, Object> res=new HashMap<String, Object>();
		res=jdbctemplate.queryForMap("SELECT COUNT(*) count FROM `customer` WHERE customer_city=? AND customer_id<>? ",city,id);
		System.out.println(res);
		long count=(long) res.get("count");
		if(count>0) {
			return false;
		}
		return true;
	}

}

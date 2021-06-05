package com.dss.SpringExample.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dss.SpringExample.entity.ProjectDetails;
import com.dss.SpringExample.service.CustomerService;

@Controller
public class DemoController {
	/*
	 * @Autowired ProjectDetails projectDetails;
	 * 
	 * @GetMapping(value="test",produces = "application/json") public @ResponseBody
	 * String getProjectName(){ return getProjectName();
	 * 
	 * }
	 */

}

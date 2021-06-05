package com.dss.SpringExample.exception;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<HashMap<String, Object>> constraintViolationException(MethodArgumentNotValidException exception){
		BindingResult result=exception.getBindingResult();
		final List<FieldError> fielderrors=result.getFieldErrors();
		HashMap<String, Object> res=new HashMap<String, Object>();
		HashMap<String, Object> errors=new HashMap<>();
		for(FieldError e:fielderrors) {
			errors.put(e.getField(),e.getDefaultMessage());
		}
		res.put("success", false);
		res.put("message", "Unable to save / update");
		res.put("errors", errors);
		return new ResponseEntity<HashMap<String,Object>>(res,HttpStatus.OK);
		
	}
	

}

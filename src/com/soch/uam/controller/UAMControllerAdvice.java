package com.soch.uam.controller;

import java.util.Locale;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.soch.uam.exception.InternalErrorException;
import com.soch.uam.exception.InvalidDataException;
import com.soch.uam.response.UserSVCResp;

@ControllerAdvice
public class UAMControllerAdvice {
	
	@Autowired
	MessageSource messageSource;
	
	
	@ExceptionHandler(InternalErrorException.class)
	@ResponseBody
	public UserSVCResp processInternalErrorException(InternalErrorException ex) {
		UserSVCResp userSVCResp = new UserSVCResp();
		userSVCResp.setResultCode(ex.getErrorCode());;
		userSVCResp.setresultString(ex.getErrorDescription());
		return userSVCResp ;
	}
	
	@ExceptionHandler(InvalidDataException.class)
	@ResponseBody
	public UserSVCResp processInvalidDataException(InvalidDataException ex) {
		UserSVCResp userSVCResp = new UserSVCResp();
		userSVCResp.setResultCode(ex.getErrorCode());;
		userSVCResp.setresultString(ex.getErrorDescription());
		return userSVCResp ;
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseBody
	public UserSVCResp processConstraintViolationException(ConstraintViolationException ex) {
		UserSVCResp userSVCResp = new UserSVCResp();
		userSVCResp.setResultCode(Integer.parseInt(messageSource.getMessage("USER.DUPLICATE.ERROR.CODE",null, Locale.getDefault())));;
		userSVCResp.setresultString(messageSource.getMessage("USER.DUPLICATE.ERROR.MSG",null, Locale.getDefault()));
		return userSVCResp ;
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	@ResponseBody
	public UserSVCResp processDataIntegrityViolationException(DataIntegrityViolationException ex) {
		UserSVCResp userSVCResp = new UserSVCResp();
		userSVCResp.setResultCode(Integer.parseInt(messageSource.getMessage("USER.DUPLICATE.ERROR.CODE",null, Locale.getDefault())));;
		userSVCResp.setresultString(messageSource.getMessage("USER.DUPLICATE.ERROR.MSG",null, Locale.getDefault()));
		return userSVCResp ;
	}

}

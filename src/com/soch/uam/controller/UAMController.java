package com.soch.uam.controller;

import java.util.Locale;

import javax.validation.Valid;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.soch.uam.dao.UserDAO;
import com.soch.uam.dto.UserDTO;
import com.soch.uam.exception.InvalidDataException;
import com.soch.uam.request.UserSVCReq;
import com.soch.uam.response.UserSVCResp;
import com.soch.uam.service.UserService;
import com.soch.uam.util.POJOCacheUtil;

@Controller
@RequestMapping("/")
public class UAMController {
	
	@Autowired
	MessageSource messageSource;
	
	@RequestMapping(value="/register/{userId}", method = RequestMethod.GET)
	public String registerUser( String userId){
		
		System.out.println("Test");
		return "Test";

    }
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	 @ResponseBody
	 public UserSVCResp signupUser(@RequestBody  UserSVCReq userSVCReq)
		{
			System.out.println(userSVCReq.getUser().getEmailId());
			
			UserDTO userDTO = userService.signUpUser(userSVCReq.getUser());
			
			UserSVCResp userSVCResp = new UserSVCResp();
			userSVCResp.setResultCode(Integer.parseInt(messageSource.getMessage("USER.REGISTRATION.SUCCUESS.CODE",null, Locale.getDefault())));
			userSVCResp.setresultString(messageSource.getMessage("USER.REGISTRATION.SUCCUESS.STATUS",null, Locale.getDefault()));
			userSVCResp.setUser(userDTO);
			return userSVCResp;
		}
	
	 @RequestMapping(value = "/checkDuplicateId", method = RequestMethod.GET)
	 @ResponseBody
	 public UserSVCResp checkDuplicateId(@RequestParam(value="userId") String userId)
		{
			UserSVCResp userSVCResp = new UserSVCResp();
			
			if(userService.isUserIdAvailable(userId))
			{
				userSVCResp.setResultCode(Integer.parseInt(messageSource.getMessage("USER.ID.AVAILABLE.CODE",null, Locale.getDefault())));
				userSVCResp.setresultString(messageSource.getMessage("USER.ID.AVAILABLE.STRING",null, Locale.getDefault()));
			}
			else {
				userSVCResp.setResultCode(Integer.parseInt(messageSource.getMessage("USER.ID.NOTAVAILABLE.CODE",null, Locale.getDefault())));
				userSVCResp.setresultString(messageSource.getMessage("USER.ID.NOTAVAILABLE.STRING",null, Locale.getDefault()));
			}
			
			return userSVCResp;
		}
	 
	 @RequestMapping(value = "/validateRegisterSVC", method = RequestMethod.GET)
	 @ResponseBody
	 public UserSVCResp validateRegisterSVC(@RequestParam(value="token") String token)
		{
			UserSVCResp userSVCResp = new UserSVCResp();
			if(userService.validateRegister(token))
			{
				userSVCResp.setResultCode(Integer.parseInt(messageSource.getMessage("USER.ID.ACTIVATION.SUCCESS.CODE",null, Locale.getDefault())));
				userSVCResp.setresultString(messageSource.getMessage("USER.ID.ACTIVATION.SUCCESS.STRING",null, Locale.getDefault()));
			}
			else
			{
				
				userSVCResp.setResultCode(Integer.parseInt(messageSource.getMessage("USER.ID.ACTIVATION.FAIL.CODE",null, Locale.getDefault())));
				userSVCResp.setresultString(messageSource.getMessage("USER.ID.ACTIVATION.FAIL.STRING",null, Locale.getDefault()));
			}
			
			return userSVCResp;
		}
	 
	 //signIn
	 
	 @RequestMapping(value = "/signIn", method = RequestMethod.POST)
	 @ResponseBody	
	 public UserSVCResp signIn(@RequestBody  UserSVCReq userSVCReq)
		{
			UserSVCResp userSVCResp = new UserSVCResp();
			UserDTO userDTO =  userService.signInUser(userSVCReq.getUser());
			if(userDTO.getUserId() == null)
			{
				userSVCResp.setResultCode(Integer.parseInt(messageSource.getMessage("USER.LOGIN.FAILURE.CODE",null, Locale.getDefault())));
				userSVCResp.setresultString(messageSource.getMessage("USER.LOGIN.FAILURE.STRING",null, Locale.getDefault()));
			}
			else if(userDTO!= null && userDTO.isLockFlag())
			{
				
				userSVCResp.setResultCode(Integer.parseInt(messageSource.getMessage("USER.ACCOUNT.LOCK.CODE",null, Locale.getDefault())));
				userSVCResp.setresultString(messageSource.getMessage("USER.ACCOUNT.LOCK.STRING",null, Locale.getDefault()));
				userSVCResp.setUser(userDTO);
			}
			else if(userDTO!= null && !userDTO.isActiveFlag())
			{
				
				userSVCResp.setResultCode(Integer.parseInt(messageSource.getMessage("USER.ACCOUNT.INACTIVE.CODE",null, Locale.getDefault())));
				userSVCResp.setresultString(messageSource.getMessage("USER.ACCOUNT.INACTIVE.STRING",null, Locale.getDefault()));
				userSVCResp.setUser(userDTO);
			}
			else if(userDTO!= null && userDTO.isActiveFlag())
			{
				
				userSVCResp.setResultCode(Integer.parseInt(messageSource.getMessage("USER.LOGIN.SUCCESS.CODE",null, Locale.getDefault())));
				userSVCResp.setresultString(messageSource.getMessage("USER.LOGIN.SUCCESS.STRING",null, Locale.getDefault()));
				userSVCResp.setUser(userDTO);
			}
			
			
			
			
			return userSVCResp;
		}
	 
	

}

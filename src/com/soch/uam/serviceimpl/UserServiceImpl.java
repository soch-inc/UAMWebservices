package com.soch.uam.serviceimpl;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.security.auth.login.LoginException;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.soch.uam.dao.UserDAO;
import com.soch.uam.domain.AddressEntity;
import com.soch.uam.domain.ConfigEntity;
import com.soch.uam.domain.LoginEntity;
import com.soch.uam.domain.SecauthtokenEntity;
import com.soch.uam.domain.SecurityQAEntity;
import com.soch.uam.domain.UserEntity;
import com.soch.uam.dto.AddressDTO;
import com.soch.uam.dto.ConfigDTO;
import com.soch.uam.dto.SecurityQADTO;
import com.soch.uam.dto.UserDTO;
import com.soch.uam.exception.InternalErrorException;
import com.soch.uam.service.UserService;
import com.soch.uam.svc.constants.APPConstants;
import com.soch.uam.util.AppUtil;
import com.soch.uam.util.CopyBeanProperties;
import com.soch.uam.util.POJOCacheUtil;
import com.soch.uam.util.SendEmail;

@Service("userService")
@PropertySource(value = { "file:c:\\harish\\application.properties"})
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDAO userDAO ;
	
	@Autowired
	private POJOCacheUtil pojoCacheUtil ;
	
	@Autowired
	private MessageSource messageSource;
	
	 @Autowired
	 private Environment environment;
	

	@Override
	@Transactional
	public UserDTO signUpUser(UserDTO userDTO) {
		
		UserEntity userEntity = new UserEntity(); 
		Set<AddressEntity> addressEntityList = new HashSet<AddressEntity>();
		Set<SecurityQAEntity> securityQAEntities = new HashSet<SecurityQAEntity>();
		AddressEntity addressEntity = null;
		SecurityQAEntity securityQAEntity = null;
		String authToken = null;
		try {
			if(userDTO.getId() == 0)
				BeanUtils.copyProperties(userEntity, userDTO);
			if(userDTO.getSecurityQA() != null && !userDTO.getSecurityQA().isEmpty())
			{
				Set<SecurityQADTO> securityQADTOs = userDTO.getSecurityQA(); 
				
				for(SecurityQADTO securityQADTO : securityQADTOs)
				{
					
					securityQAEntity = new SecurityQAEntity();
					BeanUtils.copyProperties(securityQAEntity, securityQADTO);
					securityQAEntity.setUserEntity(userEntity);
					securityQAEntity.setCreatedTs(new Date());
					securityQAEntity.setCreatedBy(userDTO.getUserId());
					
					securityQAEntity.setUpdatedTS(new Date());
					securityQAEntity.setUpdatedBy(userDTO.getUserId());
					
					securityQAEntities.add(securityQAEntity);
					
				}
				userEntity.setSecurityQA(securityQAEntities);
			}
			
			if(userDTO.getAddress() != null)
			{
				Set<AddressDTO> addressDTOs = userDTO.getAddress();
				
				for(AddressDTO address : addressDTOs)
				{
					userEntity.setId(userDTO.getId());
					addressEntity = new AddressEntity();
					BeanUtils.copyProperties(addressEntity, address);
					addressEntity.setUserEntity(userEntity);
					
					addressEntity.setCreatedTs(new Date());
					addressEntity.setCreatedBy(userEntity.getUserId());
					
					addressEntity.setUpdatedTs(new Date());
					addressEntity.setUpdatedBy(userEntity.getUserId());
					
					addressEntityList.add(addressEntity);
				}
					
				userEntity.setAddress(addressEntityList);	
			}
				
			userEntity.setCreatedTs(new Date());
			userEntity.setCreatedBy(userEntity.getUserId());
			
			userEntity.setUpdatedTs(new Date());
			userEntity.setUpdatedBy(userEntity.getUserId());
			
			//userEntity.setAddress(UASBeanUtl.convertAddressDomainSetToEntitySet(userDTO.getAddress()));
			if(userDTO.getId() == 0) {
				userEntity.setActiveFlag(false);
				userEntity.setLockFlag(false);	
			userDAO.saveUser(userEntity);
			userEntity = userDAO.getUser(userDTO.getUserId());
			userDTO.setId(userEntity.getId());
			}
			else {
				userEntity = userDAO.getUser(userDTO.getUserId());
				userEntity.setFirstName(userDTO.getFirstName());
				userEntity.setLastName(userDTO.getLastName());
				userEntity.setEmailId(userDTO.getEmailId());
				userEntity.setUpdatedBy(userEntity.getUserId());
				userEntity.setUpdatedTs(new Date());
				userEntity.setDateOfBirth(convertDate(userDTO.getDateOfBirth()));
				userEntity.setAddress(addressEntityList);
				userDAO.updateUser(userEntity);
				authToken =generateAuthToken(userEntity);
				sendRegEmail(userEntity, authToken);
			}
			
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new InternalErrorException(Integer.parseInt(messageSource.getMessage("INTERNAL.SYSTEM.ERROR.CODE",null, Locale.getDefault())), 
					messageSource.getMessage("INTERNAL.SYSTEM.ERROR.MSG",null, Locale.getDefault()));
		}
		return userDTO;
	}
	
	private Date convertDate(String javaDate)
	{
		SimpleDateFormat javaDateFormat = new SimpleDateFormat("MM-dd-yyyy");
		SimpleDateFormat JSONDateFormat = new SimpleDateFormat("yyy-MM-dd");
		try {
			Date date = javaDateFormat.parse(javaDate);
			return date;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	 * @param userEntity
	 * @param authToken
	 * @return
	 */
	private boolean sendRegEmail(UserEntity userEntity, String authToken)
	{
		String toEmail = null;
		boolean returnVal = true;
		if(userEntity.getEmailId() != null)
			toEmail = userEntity.getEmailId();
		else
			toEmail = userEntity.getUserId();
		String emailBody = APPConstants.REG_EMAIL_TEXT;
		String url = "http://localhost:8080"+APPConstants.REG_EMAIL_URL.replace("tokenParam", authToken);
		emailBody = emailBody.replace("url", url);
		
		SendEmail.sendHTMLEmail(toEmail, APPConstants.REG_EMAIL_SUB, emailBody);
		return returnVal;
	}


	@Override
	@Transactional
	public boolean isUserIdAvailable(String userId) {
		boolean returnValue = false;
		UserEntity userEntity = userDAO.getUser(userId);
		if(userEntity == null)
			returnValue = true;
		return returnValue;
	}
	
	public String generateAuthToken(UserEntity userEntity)
	{
		String authtokenID = UUID.randomUUID().toString().replaceAll("-", "");
		SecauthtokenEntity secauthtokenEntity = new SecauthtokenEntity();
		secauthtokenEntity.setAuthToken(authtokenID);
		secauthtokenEntity.setLastAccessTs(new Date());
		secauthtokenEntity.setUserEntity(userEntity);
		secauthtokenEntity.setCreatedTs(new Date());
		secauthtokenEntity.setStatus(true);
		secauthtokenEntity.setCreatedBy(userEntity.getUserId());
		userDAO.saveAuthToken(secauthtokenEntity);
		return authtokenID;
	}

	@Override
	@Transactional
	public boolean validateRegister(String authToken) {
		boolean returnValue = false;
		SecauthtokenEntity secauthtokenEntity = userDAO.getAuthToken(authToken);
		Date createdTS = null;
		if(secauthtokenEntity != null)
		{
			createdTS = secauthtokenEntity.getCreatedTs();
			if(secauthtokenEntity.getStatus() && AppUtil.validateTime(Integer.parseInt(environment.getRequiredProperty("regValidinMins")), createdTS))
			{
					secauthtokenEntity.setStatus(false);
					UserEntity userEntity = secauthtokenEntity.getUserEntity();
					userEntity.setActiveFlag(true);
					userDAO.updateAuthToken(secauthtokenEntity);
					userEntity.setUpdatedTs(new Date());
					userDAO.updateUser(userEntity);
					returnValue =  true;
			}
		}
		return returnValue;
	}

	@Override
	@Transactional
	public UserDTO signInUser(UserDTO userDTO) {
		
		UserEntity userEntity = null;
		UserDTO returnUserDTO = new UserDTO();
		userEntity = userDAO.getUser(userDTO.getUserId());
		Map<String, String> cacheMap = pojoCacheUtil.getAppConfig();
		if(userEntity!= null )
		{
			System.out.println(userEntity.getLockFlag());
			if(userEntity.getLockFlag())
			{
				returnUserDTO.setLockFlag(userEntity.getLockFlag());
				returnUserDTO.setActiveFlag(userEntity.getActiveFlag());
				returnUserDTO.setUserId(userEntity.getUserId());
			}else if(!userEntity.getActiveFlag())
			{
				returnUserDTO.setActiveFlag(userEntity.getActiveFlag());
				returnUserDTO.setUserId(userEntity.getUserId());
			}
			else if(userEntity.getPassowrd().equals(userDTO.getPassowrd()))
			{
			LoginEntity loginEntity = new LoginEntity();
			loginEntity.setUserEntity(userEntity);
			loginEntity.setLoginTs(new Date());
			loginEntity.setLoginStatus(true);
			userDAO.saveUserLogin(loginEntity);
			returnUserDTO = CopyBeanProperties.copyUserPerperties(userEntity);
				if(userEntity.getLogintEntity().iterator().hasNext())
					returnUserDTO.setLastLoggedin(userEntity.getLogintEntity().iterator().next().getLoginTs().toString());
			}
			else
			{
				userEntity.setLoginFailureCount(userEntity.getLoginFailureCount()+1);
				if(userEntity.getLoginFailureCount() == Integer.parseInt(cacheMap.get("LOGIN_FAILURE_COUNT")))
					userEntity.setLockFlag(true);
				userDAO.updateUser(userEntity);
			}
		}
		else
		{
			
		}
		
		return returnUserDTO;
	}

	@Override
	public List<ConfigDTO> getAppConfig() {
		// TODO Auto-generated method stub
		List<ConfigEntity> configEntities = userDAO.getAppConfig();
		List<ConfigDTO> configDTOs = new ArrayList<ConfigDTO>();
		ConfigDTO configDTO = null;
		
		
		
		for(ConfigEntity configEntity : configEntities)
		{
			configDTO = new ConfigDTO();
			try {
				BeanUtils.copyProperties(configDTO, configEntity);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			
			configDTOs.add(configDTO);
		}
		return configDTOs;
	}
	

}

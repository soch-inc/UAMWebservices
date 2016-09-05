package com.soch.uam.util;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import com.soch.uam.domain.AddressEntity;
import com.soch.uam.domain.SecurityQAEntity;
import com.soch.uam.domain.UserEntity;
import com.soch.uam.dto.AddressDTO;
import com.soch.uam.dto.SecurityQADTO;
import com.soch.uam.dto.UserDTO;

public class UASBeanUtil {
	
	static UserEntity converUserDomanToEntity(UserDTO user)
	{
		
		return null;
	}
	
	
	static SecurityQAEntity convertSecurityQADomainToEntity(SecurityQADTO securiQA)
	{
		return null;	
	}
	
	public static UserDTO converUserEntityToDomain(UserEntity userEntity)
	{
		UserDTO user = new UserDTO();
		try {
			BeanUtils.copyProperties(user, userEntity);
			} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return user;
	}
	
	public static AddressDTO convertAddressEntityToDomain(AddressEntity addressEntity)
	{
		AddressDTO address = new AddressDTO();
		try {
			BeanUtils.copyProperties(address, addressEntity);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return address;
	}
	
	public static List<AddressDTO> convertAddressEntitySetToDomainSet(List<AddressEntity> addressEntitySet)
	{
		AddressDTO address;
		List<AddressDTO> addresseSet = new ArrayList<AddressDTO>();
		for(AddressEntity addressEntity : addressEntitySet)
		{
			address = convertAddressEntityToDomain(addressEntity);
			addresseSet.add(address);
		}
		return addresseSet;
	}
	
	public static AddressEntity convertAddressDomainToEntity(AddressDTO address)
	{
		AddressEntity addressEntity = new AddressEntity();
		try {
			BeanUtils.copyProperties(addressEntity,address);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return addressEntity;
	}
	
	public static List<AddressEntity> convertAddressDomainSetToEntitySet(List<AddressDTO> addressSet)
	{
		AddressEntity addressEntity;
		List<AddressEntity> addresseSet = new ArrayList<AddressEntity>();
		for(AddressDTO address : addressSet)
		{
			addressEntity = convertAddressDomainToEntity(address);
			addresseSet.add(addressEntity);
		}
		return addresseSet;
	}
	
	static SecurityQADTO convertSecurityQAEntityToDomain(SecurityQAEntity securityQAEntity)
	{
		SecurityQADTO securityQA = new SecurityQADTO();
		
		try {
			BeanUtils.copyProperties(securityQA, securityQAEntity);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return securityQA;
	}
	
	public static List<SecurityQADTO> convertSecurityQAEntitySetToDomainSet(List<SecurityQAEntity> securityQAEntities)
	{
		List<SecurityQADTO> securityQASet = new ArrayList<SecurityQADTO>();
		SecurityQADTO securityQA ;
		for(SecurityQAEntity securityQAEntity : securityQAEntities)
		{
			securityQA = convertSecurityQAEntityToDomain(securityQAEntity);
			securityQASet.add(securityQA);
		}
			
		return securityQASet;
	}

}

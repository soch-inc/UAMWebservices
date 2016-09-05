package com.soch.uam.util;

import com.soch.uam.domain.UserEntity;
import com.soch.uam.dto.UserDTO;

public class CopyBeanProperties {
	
	public static UserDTO copyUserPerperties(UserEntity userEntity)
	{
		UserDTO userDTO = new UserDTO();
		userDTO.setFirstName(userEntity.getFirstName());
		userDTO.setLastName(userEntity.getLastName());
		userDTO.setUserId(userEntity.getUserId());
		userDTO.setActiveFlag(userEntity.getActiveFlag());
		return userDTO; 
	}

}

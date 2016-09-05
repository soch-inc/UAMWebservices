package com.soch.uam.service;

import java.util.List;

import com.soch.uam.domain.ConfigEntity;
import com.soch.uam.dto.ConfigDTO;
import com.soch.uam.dto.UserDTO;

public interface UserService {
		public UserDTO signUpUser(UserDTO userDTO);
		public boolean isUserIdAvailable(String userId);
		//checkDuplicateId
		boolean validateRegister(String authToken);
		public UserDTO signInUser(UserDTO userDTO);
		public List<ConfigDTO> getAppConfig();
}

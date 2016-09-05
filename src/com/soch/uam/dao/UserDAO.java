package com.soch.uam.dao;

import java.util.List;

import com.soch.uam.domain.ConfigEntity;
import com.soch.uam.domain.LoginEntity;
import com.soch.uam.domain.SecauthtokenEntity;
import com.soch.uam.domain.UserEntity;

public interface UserDAO {
	
	public void saveUser(UserEntity userEntity);
	
	public void updateUser(UserEntity userEntity);
	
	public UserEntity getUser(String userId);
	
	public void saveAuthToken(SecauthtokenEntity secauthtokenEntity);
	
	public SecauthtokenEntity getAuthToken(String authToken);

	void updateAuthToken(SecauthtokenEntity secauthtokenEntity);
	
	public void saveUserLogin(LoginEntity loginEntity);
	
	public List<ConfigEntity> getAppConfig();

}

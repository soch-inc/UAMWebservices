package com.soch.uam.daoimpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.soch.uam.dao.UserDAO;
import com.soch.uam.domain.ConfigEntity;
import com.soch.uam.domain.LoginEntity;
import com.soch.uam.domain.SecauthtokenEntity;
import com.soch.uam.domain.UserEntity;

@Component
public class UserDAOImpl implements UserDAO{
	
	@Autowired
    private SessionFactory sessionFactory;
	
	@Override
	public void saveUser(UserEntity userEntity) {
		try {
			this.sessionFactory.getCurrentSession().save(userEntity);		
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		
	}

	@Override
	public UserEntity getUser(String userId) {
		
		List<UserEntity> userList;
		UserEntity userEntity = null;
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(UserEntity.class);
		criteria.add(Restrictions.eq("userId", userId));
		//criteria.setFetchMode("address", FetchMode.JOIN);
		//criteria.setFetchMode("securityQA", FetchMode.JOIN);
		userList = criteria.list();
		if(userList.size() > 0) {
			userEntity = userList.get(0);
		}
		
		return userEntity;
	}

	@Override
	public void updateUser(UserEntity userEntity) {
		try {
			this.sessionFactory.getCurrentSession().update(userEntity);		
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		
	}

	@Override
	public void saveAuthToken(SecauthtokenEntity secauthtokenEntity) {
			this.sessionFactory.getCurrentSession().save(secauthtokenEntity);		
	}
	
	@Override
	public void updateAuthToken(SecauthtokenEntity secauthtokenEntity) {
			this.sessionFactory.getCurrentSession().update(secauthtokenEntity);		
	}

	@SuppressWarnings("unchecked")
	@Override
	public SecauthtokenEntity getAuthToken(String authToken) {
		SecauthtokenEntity secauthtokenEntity = null;
		
		List<SecauthtokenEntity> secauthtokenEntities;
		UserEntity userEntity = null;
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(SecauthtokenEntity.class);
		criteria.add(Restrictions.eq("authToken", authToken));
		secauthtokenEntities = criteria.list();
		if(secauthtokenEntities.size() > 0)
			secauthtokenEntity = secauthtokenEntities.get(0);
		
		return secauthtokenEntity;
	}
	
	@Override
	public void saveUserLogin(LoginEntity loginEntity) {
			this.sessionFactory.getCurrentSession().save(loginEntity);		
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<ConfigEntity> getAppConfig() {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ConfigEntity.class);
		
		List<ConfigEntity> configEntities = criteria.list();
		
		return configEntities;
	}

}

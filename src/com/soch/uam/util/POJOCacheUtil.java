package com.soch.uam.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.InitialContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.soch.uam.dto.ConfigDTO;
import com.soch.uam.service.UserService;
import com.sun.beans.util.Cache;

@Component
public class POJOCacheUtil {
	
	private static Map<String, String>  objectCache = null;
	
	@Autowired
	UserService userService;
	
	public Map<String, String> getAppConfig()
	{
		if(objectCache == null)
		{
			List<ConfigDTO> configDTOs = userService.getAppConfig();
			objectCache = new HashMap<String, String>();
			for(ConfigDTO configDTO : configDTOs)
			{
				objectCache.put(configDTO.getConfigName(), configDTO.getConfigValue());
			}
		}
		return objectCache;
	}
}

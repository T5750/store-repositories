package com.backyard.datasources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backyard.datasources.annotation.DataSource;
import com.backyard.modules.api.entity.UserEntity;
import com.backyard.modules.api.service.UserService;

/**
 * 测试
 */
@Service
public class DataSourceTestService {
	@Autowired
	private UserService userService;

	public UserEntity queryObject(Long userId) {
		return userService.queryObject(userId);
	}

	@DataSource(name = DataSourceNames.SECOND)
	public UserEntity queryObject2(Long userId) {
		return userService.queryObject(userId);
	}
}

package com.backyard.modules.api.dao;

import org.apache.ibatis.annotations.Mapper;

import com.backyard.modules.api.entity.UserEntity;
import com.backyard.modules.sys.dao.BaseDao;

/**
 * 用户
 */
@Mapper
public interface UserDao extends BaseDao<UserEntity> {
	UserEntity queryByMobile(String mobile);
}

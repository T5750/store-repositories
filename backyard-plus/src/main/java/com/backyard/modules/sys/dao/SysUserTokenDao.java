package com.backyard.modules.sys.dao;

import org.apache.ibatis.annotations.Mapper;

import com.backyard.modules.sys.entity.SysUserTokenEntity;

/**
 * 系统用户Token
 */
@Mapper
public interface SysUserTokenDao extends BaseDao<SysUserTokenEntity> {
	SysUserTokenEntity queryByUserId(Long userId);

	SysUserTokenEntity queryByToken(String token);
}

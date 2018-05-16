package com.backyard.modules.api.dao;

import org.apache.ibatis.annotations.Mapper;

import com.backyard.modules.api.entity.TokenEntity;
import com.backyard.modules.sys.dao.BaseDao;

/**
 * 用户Token
 */
@Mapper
public interface TokenDao extends BaseDao<TokenEntity> {
	TokenEntity queryByUserId(Long userId);

	TokenEntity queryByToken(String token);
}

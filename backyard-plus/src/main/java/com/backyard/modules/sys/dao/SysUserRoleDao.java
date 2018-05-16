package com.backyard.modules.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.backyard.modules.sys.entity.SysUserRoleEntity;

/**
 * 用户与角色对应关系
 */
@Mapper
public interface SysUserRoleDao extends BaseDao<SysUserRoleEntity> {
	/**
	 * 根据用户ID，获取角色ID列表
	 */
	List<Long> queryRoleIdList(Long userId);
}

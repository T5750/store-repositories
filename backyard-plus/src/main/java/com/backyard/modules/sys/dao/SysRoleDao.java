package com.backyard.modules.sys.dao;

import org.apache.ibatis.annotations.Mapper;

import com.backyard.modules.sys.entity.SysRoleEntity;

/**
 * 角色管理
 */
@Mapper
public interface SysRoleDao extends BaseDao<SysRoleEntity> {
}

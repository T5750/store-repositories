package com.backyard.modules.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.backyard.modules.sys.entity.SysDeptEntity;

/**
 * 部门管理
 */
@Mapper
public interface SysDeptDao extends BaseDao<SysDeptEntity> {
	/**
	 * 查询子部门ID列表
	 * 
	 * @param parentId
	 *            上级部门ID
	 */
	List<Long> queryDetpIdList(Long parentId);
}

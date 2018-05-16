package com.backyard.modules.oss.dao;

import org.apache.ibatis.annotations.Mapper;

import com.backyard.modules.oss.entity.SysOssEntity;
import com.backyard.modules.sys.dao.BaseDao;

/**
 * 文件上传
 */
@Mapper
public interface SysOssDao extends BaseDao<SysOssEntity> {
}

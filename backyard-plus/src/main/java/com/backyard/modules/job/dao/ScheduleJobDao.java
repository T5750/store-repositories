package com.backyard.modules.job.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.backyard.modules.job.entity.ScheduleJobEntity;
import com.backyard.modules.sys.dao.BaseDao;

/**
 * 定时任务
 */
@Mapper
public interface ScheduleJobDao extends BaseDao<ScheduleJobEntity> {
	/**
	 * 批量更新状态
	 */
	int updateBatch(Map<String, Object> map);
}

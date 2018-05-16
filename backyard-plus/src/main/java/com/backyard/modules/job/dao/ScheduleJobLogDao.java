package com.backyard.modules.job.dao;

import org.apache.ibatis.annotations.Mapper;

import com.backyard.modules.job.entity.ScheduleJobLogEntity;
import com.backyard.modules.sys.dao.BaseDao;

/**
 * 定时任务日志
 */
@Mapper
public interface ScheduleJobLogDao extends BaseDao<ScheduleJobLogEntity> {
}

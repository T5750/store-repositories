package com.backyard.modules.job.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backyard.common.utils.PageUtils;
import com.backyard.common.utils.Query;
import com.backyard.common.utils.R;
import com.backyard.modules.job.entity.ScheduleJobLogEntity;
import com.backyard.modules.job.service.ScheduleJobLogService;

/**
 * 定时任务日志
 */
@RestController
@RequestMapping("/sys/scheduleLog")
public class ScheduleJobLogController {
	@Autowired
	private ScheduleJobLogService scheduleJobLogService;

	/**
	 * 定时任务日志列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("sys:schedule:log")
	public R list(@RequestParam Map<String, Object> params) {
		// 查询列表数据
		Query query = new Query(params);
		List<ScheduleJobLogEntity> jobList = scheduleJobLogService
				.queryList(query);
		int total = scheduleJobLogService.queryTotal(query);
		PageUtils pageUtil = new PageUtils(jobList, total, query.getLimit(),
				query.getPage());
		return R.ok().put("page", pageUtil);
	}

	/**
	 * 定时任务日志信息
	 */
	@RequestMapping("/info/{logId}")
	public R info(@PathVariable("logId") Long logId) {
		ScheduleJobLogEntity log = scheduleJobLogService.queryObject(logId);
		return R.ok().put("log", log);
	}
}

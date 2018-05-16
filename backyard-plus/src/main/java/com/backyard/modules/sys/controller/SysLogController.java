package com.backyard.modules.sys.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.backyard.common.utils.PageUtils;
import com.backyard.common.utils.Query;
import com.backyard.common.utils.R;
import com.backyard.modules.sys.entity.SysLogEntity;
import com.backyard.modules.sys.service.SysLogService;

/**
 * 系统日志
 */
@Controller
@RequestMapping("/sys/log")
public class SysLogController {
	@Autowired
	private SysLogService sysLogService;

	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("sys:log:list")
	public R list(@RequestParam Map<String, Object> params) {
		// 查询列表数据
		Query query = new Query(params);
		List<SysLogEntity> sysLogList = sysLogService.queryList(query);
		int total = sysLogService.queryTotal(query);
		PageUtils pageUtil = new PageUtils(sysLogList, total, query.getLimit(),
				query.getPage());
		return R.ok().put("page", pageUtil);
	}
}

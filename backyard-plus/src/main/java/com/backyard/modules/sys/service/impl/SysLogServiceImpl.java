package com.backyard.modules.sys.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backyard.modules.sys.dao.SysLogDao;
import com.backyard.modules.sys.entity.SysLogEntity;
import com.backyard.modules.sys.service.SysLogService;

@Service("sysLogService")
public class SysLogServiceImpl implements SysLogService {
	@Autowired
	private SysLogDao sysLogDao;

	@Override
	public SysLogEntity queryObject(Long id) {
		return sysLogDao.queryObject(id);
	}

	@Override
	public List<SysLogEntity> queryList(Map<String, Object> map) {
		return sysLogDao.queryList(map);
	}

	@Override
	public int queryTotal(Map<String, Object> map) {
		return sysLogDao.queryTotal(map);
	}

	@Override
	public void save(SysLogEntity sysLog) {
		sysLogDao.save(sysLog);
	}

	@Override
	public void delete(Long id) {
		sysLogDao.delete(id);
	}

	@Override
	public void deleteBatch(Long[] ids) {
		sysLogDao.deleteBatch(ids);
	}
}

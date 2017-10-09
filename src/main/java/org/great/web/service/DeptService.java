package org.great.web.service;

import java.util.List;

import javax.annotation.Resource;

import org.great.web.bean.CostInfo;
import org.great.web.mapper.CostInfoMapper;
import org.great.web.mapper.DeptMapper;
import org.great.web.mapper.SysUserMapper;
import org.springframework.stereotype.Service;

/**
 * 用户表操作
 * 
 * @author 谢军
 * 
 */
@Service
public class DeptService {
	@Resource
	private DeptMapper deptMapper;

}

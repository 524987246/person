package org.great.web.service.sys;

import java.util.List;

import javax.annotation.Resource;

import org.great.web.bean.buz.CostInfo;
import org.great.web.mapper.buz.CostInfoMapper;
import org.great.web.mapper.sys.SysUserMapper;
import org.springframework.stereotype.Service;

/**
 * 用户表操作
 * 
 * @author 谢军
 * 
 */
@Service
public class SysUserService {
	@Resource
	private SysUserMapper sysUserMapper;

}

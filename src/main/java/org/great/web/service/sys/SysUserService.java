package org.great.web.service.sys;

import javax.annotation.Resource;

import org.great.web.mapper.sys.SysUserMapper;
import org.springframework.stereotype.Service;

/**
 * 
 * @author xiejun
 * 
 */
@Service
public class SysUserService {
	@Resource
	private SysUserMapper sysUserMapper;

}

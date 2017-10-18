package org.great.web.service.sys;

import java.util.List;

import javax.annotation.Resource;

import org.great.web.bean.sys.Dept;
import org.great.web.mapper.sys.DeptMapper;
import org.springframework.stereotype.Service;

/**
 * 
 * @author xiejun
 * 
 */
@Service
public class DeptService {
	@Resource
	private DeptMapper deptMapper;

	public Dept get(Long id) {
		Dept dept = deptMapper.get(id);
		return dept;
	}

	public List<Dept> findList(Dept dept) {
		List<Dept> list = deptMapper.findList(dept);
		return list;
	}

	public int save(Dept dept) {
		dept.setBaseInfo();
		int i = deptMapper.save(dept);
		return i;
	}

	public int update(Dept dept) {
		dept.setBaseInfo();
		int i = deptMapper.update(dept);
		return i;
	}

	public int delete(Dept dept) {
		dept.setIsemploy(2);
		dept.setBaseInfo();
		int i = deptMapper.delete(dept);
		return i;
	}

	public int batchdelete(Dept dept) {
		dept.setIsemploy(2);
		dept.setBaseInfo();
		int i = deptMapper.batchdelete(dept);
		return i;
	}

	public Integer queryTotal(Dept dept) {
		Integer totalCount = deptMapper.queryTotal(dept);
		return totalCount;
	}

}

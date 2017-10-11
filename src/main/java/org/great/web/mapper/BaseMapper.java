package org.great.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.great.web.bean.buz.CostInfo;
import org.great.web.bean.sys.Dept;

/**
 * 基础mapper
 * 
 * @author xiejun
 * 
 */
public interface BaseMapper<T> {

	public T get(Long id);

	public List<T> findList(T t);

	public Integer queryTotal(T t);

	public int save(T t);

	public int update(T t);

	public int delete(T t);

	public int batchdelete(List<Long> list);
}

package org.great.web.mapper;

import java.util.List;

/**
 * 基础mapper
 * 
 * @author xiejun
 * 
 */
public interface BaseMapper<T> {

	public T get(Long id);

	public List<T> findList(T t);

	public Long queryTotal(T t);

	public int save(T t);

	public int update(T t);

	public int delete(T t);

	public int batchdelete(T t);
}

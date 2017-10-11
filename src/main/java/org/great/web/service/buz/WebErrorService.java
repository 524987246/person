package org.great.web.service.buz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;

import javax.annotation.Resource;

import org.great.cache.RedisUtil;
import org.great.web.bean.buz.WebError;
import org.great.web.mapper.buz.WebErrorMapper;
import org.springframework.stereotype.Service;

/**
 * 用户表操作
 * 
 * @author 谢军
 * 
 */
@Service
public class WebErrorService {
	@Resource
	private WebErrorMapper webErrorMapper;
	@Autowired
	private RedisUtil redisUtil;
	
	//@Cacheable(value = "weberrorlist", keyGenerator = "KeyGenerator")
	public List<WebError> findWebErrorByWebError(WebError webError,
			int page_new, int page_num) {
		List<WebError> list = webErrorMapper.findWebErrorByWebError(webError,
				page_new, page_num);
		return list;
	}

	/**
	 * 更改数据状态
	 * 
	 * @param sid
	 *            主键
	 * @param isemploy
	 *            状态
	 * @return true 成功 false 失败
	 */
	public boolean delWebErrorBySid(String sid, Integer isemploy) {
		int i = 0;
		if (sid.lastIndexOf(",") != -1) {
			String[] arraysid = sid.split(",");
			for (int j = 0; j < arraysid.length; j++) {
				i = webErrorMapper.delWebErrorBySid(
						Integer.valueOf(arraysid[j]), isemploy);
				if (i == 0) {
					break;
				}
			}
		} else {
			i = webErrorMapper.delWebErrorBySid(Integer.valueOf(sid), isemploy);
		}

		boolean bo = false;
		if (i > 0) {
			bo = true;
		}
		return bo;
	}

	/**
	 * 添加
	 * 
	 * @param webError
	 * @return true 成功 false 失败
	 */
	public boolean insertWebError(WebError webError) {
		int i = webErrorMapper.insertWebError(webError);
		boolean bo = false;
		if (i > 0) {
			bo = true;
		}
		return bo;
	}

	/**
	 * 更新信息
	 * 
	 * @param webError
	 * @return true 成功 false 失败
	 */
	public boolean updateWebErrorBySid(WebError webError) {
		int i = webErrorMapper.updateWebErrorBySid(webError);
		boolean bo = false;
		if (i > 0) {
			bo = true;
		}
		return bo;
	}
}

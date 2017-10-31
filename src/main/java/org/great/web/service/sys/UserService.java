package org.great.web.service.sys;

import java.util.List;

import javax.annotation.Resource;

import org.great.web.bean.sys.User;
import org.great.web.mapper.sys.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author xiejun
 */
@Service
public class UserService {
	@Resource
	private UserMapper usermapper;

	/**
	 * 用于查找用户
	 * 
	 * @param user
	 * @return 数据库查询出的对象
	 */
	public User get(User user) {
		User loginuser = usermapper.get(user);
		return loginuser;
	}

	public User get(Long id) {
		User user = new User();
		user.setId(id);
		user = usermapper.get(user);
		return user;
	}

	public List<User> findList(User user) {
		List<User> list = usermapper.findList(user);
		return list;
	}

	@Transactional(readOnly = false)
	public int save(User user) {
		user.setBaseInfo();
		int i = usermapper.save(user);
		return i;
	}

	@Transactional(readOnly = false)
	public int update(User user) {
		user.setBaseInfo();
		int i = usermapper.update(user);
		return i;
	}

	@Transactional(readOnly = false)
	public int delete(User user) {
		user.setIsemploy(3);
		user.setBaseInfo();
		int i = usermapper.delete(user);
		return i;
	}

	@Transactional(readOnly = false)
	public int batchdelete(User user) {
		user.setIsemploy(3);
		user.setBaseInfo();
		int i = usermapper.batchdelete(user);
		return i;
	}

	@Transactional(readOnly = false)
	public Long queryTotal(User user) {
		Long totalCount = usermapper.queryTotal(user);
		return totalCount;
	}

	public List<User> checkLoginName(User user) {
		List<User> list = usermapper.checkLoginName(user);// 登录名称验证
		return list;
	}

}

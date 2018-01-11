package org.great.web.service.buz;

import java.util.List;

import javax.annotation.Resource;

import org.great.web.bean.buz.BuzLinkman;
import org.great.web.mapper.buz.BuzLinkmanMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author xiejun
 * 
 */
@Service
@Transactional(readOnly = true)
public class BuzLinkmanService {
	@Resource
	private BuzLinkmanMapper buzLinkmanMapper;

	public BuzLinkman get(Long id) {
		BuzLinkman buzLinkman = buzLinkmanMapper.get(id);
		return buzLinkman;
	}

	public List<BuzLinkman> findList(BuzLinkman buzLinkman) {
		List<BuzLinkman> list = buzLinkmanMapper.findList(buzLinkman);
		return list;
	}

	@Transactional(readOnly = false)
	public int save(BuzLinkman buzLinkman) {
		buzLinkman.setBaseInfo();
		int i = buzLinkmanMapper.save(buzLinkman);
		return i;
	}

	@Transactional(readOnly = false)
	public int update(BuzLinkman buzLinkman) {
		buzLinkman.setBaseInfo();
		int i = buzLinkmanMapper.update(buzLinkman);
		return i;
	}

	@Transactional(readOnly = false)
	public int delete(BuzLinkman buzLinkman) {
		buzLinkman.setIsemploy(2);
		buzLinkman.setBaseInfo();
		int i = buzLinkmanMapper.delete(buzLinkman);
		return i;
	}

	@Transactional(readOnly = false)
	public int batchdelete(BuzLinkman buzLinkman) {
		buzLinkman.setIsemploy(2);
		buzLinkman.setBaseInfo();
		int i = buzLinkmanMapper.batchdelete(buzLinkman);
		return i;
	}

	@Transactional(readOnly = false)
	public Long queryTotal(BuzLinkman buzLinkman) {
		Long totalCount = buzLinkmanMapper.queryTotal(buzLinkman);
		return totalCount;
	}
}

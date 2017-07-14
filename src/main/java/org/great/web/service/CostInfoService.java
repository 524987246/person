package org.great.web.service;

import java.util.List;

import javax.annotation.Resource;

import org.great.web.bean.CostInfo;
import org.great.web.mapper.CostInfoMapper;
import org.springframework.stereotype.Service;

/**
 * 用户表操作
 * 
 * @author 谢军
 * 
 */
@Service
public class CostInfoService {
	@Resource
	private CostInfoMapper CostInfoMapper;

	public List<CostInfo> findCostInfoByCostInfo(CostInfo CostInfo,
			int page_new, int page_num) {
		List<CostInfo> list = CostInfoMapper.findCostInfoByCostInfo(CostInfo,
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
	public boolean delCostInfoBySid(String sid, Integer isemploy) {
		int i = 0;
		if (sid.lastIndexOf(",") != -1) {
			String[] arraysid = sid.split(",");
			for (int j = 0; j < arraysid.length; j++) {
				i = CostInfoMapper.delCostInfoBySid(
						Integer.valueOf(arraysid[j]), isemploy);
				if (i == 0) {
					break;
				}
			}
		} else {
			i = CostInfoMapper.delCostInfoBySid(Integer.valueOf(sid), isemploy);
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
	 * @param CostInfo
	 * @return true 成功 false 失败
	 */
	public boolean insertCostInfo(CostInfo CostInfo) {
		int i = CostInfoMapper.insertCostInfo(CostInfo);
		boolean bo = false;
		if (i > 0) {
			bo = true;
		}
		return bo;
	}

	/**
	 * 更新信息
	 * 
	 * @param CostInfo
	 * @return true 成功 false 失败
	 */
	public boolean updateCostInfoBySid(CostInfo CostInfo) {
		int i = CostInfoMapper.updateCostInfoBySid(CostInfo);
		boolean bo = false;
		if (i > 0) {
			bo = true;
		}
		return bo;
	}

	/**
	 * 获取总额
	 * @param costInfo
	 * @return
	 */
	public double costSumByCostInfo(CostInfo costInfo) {
		return CostInfoMapper.costSumByCostInfo(costInfo);
	}
}

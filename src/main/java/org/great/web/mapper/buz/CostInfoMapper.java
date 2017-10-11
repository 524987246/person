package org.great.web.mapper.buz;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.great.web.bean.buz.CostInfo;

/**
 * 消费信息mapper
 * 
 * @author 谢军
 * 
 */
public interface CostInfoMapper {

	/**
	 * 查找
	 * 
	 * @param
	 * @return
	 */
	List<CostInfo> findCostInfoByCostInfo(@Param("costInfo") CostInfo costInfo,
			@Param("page_new") int page_new, @Param("page_num") int page_num);

	/**
	 * 删除
	 * 
	 * @param sid
	 *            主键id
	 * @param isemploy
	 *            状态
	 * @return
	 */
	int delCostInfoBySid(@Param("sid") Integer sid,
			@Param("isemploy") Integer isemploy);

	/**
	 * 添加
	 * 
	 * @param costInfo
	 *            对象
	 * @return
	 */
	int insertCostInfo(CostInfo costInfo);

	/**
	 * 更新
	 * 
	 * @param costInfo对象
	 * @return
	 */
	int updateCostInfoBySid(CostInfo costInfo);
	
	/**
	 * 查询金额总数
	 * @param costInfo
	 * @return
	 */
	Double costSumByCostInfo(CostInfo costInfo);
}

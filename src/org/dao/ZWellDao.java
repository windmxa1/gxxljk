package org.dao;

import java.util.List;

import org.model.ZWell;

public interface ZWellDao {
	/**
	 * 增加或修改
	 */
	public Long saveOrUpdate(ZWell zWell);

	/**
	 * 删除井位
	 */
	public boolean delete(Long id);

	/**
	 * 获取井位列表
	 */
	public List<ZWell> getList(Integer start, Integer limit, Boolean isCentral,
			Long userId);

	/**
	 * 获取井位总数
	 */
	public Long getCount(Boolean isCentral, Long userId);

	/**
	 * 搜索光纤对应的井位
	 */
	public List<ZWell> getList(Integer start, Integer limit, Long hostId);

	/**
	 * 获取光纤对应的井位总数
	 */
	public Long getCount(Long hostId);

}

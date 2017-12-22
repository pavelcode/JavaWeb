package com.cblue.shop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cblue.shop.mapper.TbItemMapper;
import com.cblue.shop.pojo.TbItem;
import com.cblue.shop.pojo.TbItemExample;
import com.cblue.shop.pojo.TbItemExample.Criteria;
import com.cblue.shop.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private TbItemMapper itemMapper;

	@Override
	public TbItem getItemById(Long id) {
		// 两种方式
		//方式1
		// 根据主键查询
		// TbItem tbItem = itemMapper.selectByPrimaryKey(itemId);
		//方式2
		TbItemExample example = new TbItemExample();
		Criteria criteria = example.createCriteria();
		// 设置查询条件
		criteria.andIdEqualTo(id);
		// 执行查询
		List<TbItem> list = itemMapper.selectByExample(example);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

}

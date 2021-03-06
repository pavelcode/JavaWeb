package com.cblue.shop.service;

import main.java.com.cblue.common.pojo.EasyUIDataGridResult;

import com.cblue.shop.pojo.TbItem;

public interface ItemService {
	
	 TbItem  getItemById(Long id);
	 
	 /**
	  * 
	  * @param currengPage 当前页数
	  * @param pageSize 每页显示条数
	  * @return
	  */
	 EasyUIDataGridResult getItemListByPage(int currengPage,int pageSize);

}

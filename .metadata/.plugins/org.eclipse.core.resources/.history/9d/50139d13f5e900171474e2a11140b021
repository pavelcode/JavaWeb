package com.cblue.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cblue.shop.pojo.TbItem;
import com.cblue.shop.service.ItemService;

@Controller
public class ItemController {
	

	@Autowired
	private ItemService itemService;
	/**
	 * @ResponseBody 返回的结果是json格式
	 * @PathVariable 从请求路径中获得itemid值
	 */
	@RequestMapping("/item/{itemId}")
	@ResponseBody
	public TbItem getItemById(@PathVariable Long itemId) {
		TbItem tbItem = itemService.getItemById(itemId);
		return tbItem;
	}

}

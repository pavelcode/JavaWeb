package com.cblue.oa.base;

import java.util.List;

import com.cblue.oa.utils.HQLUtils;
import com.cblue.oa.utils.PageBean;

public interface IBaseDao<T> {
	
	//添加
	public void add(T entity);
	
	//删除
	public void delete(Long id);
	
	//修改
	public void update(T entity);
	
	//根据id查询
	public T getById(Long id);
	
	//一次查询多个对象
	public List<T> getByIds(Long[] ids);
	
	//查询所有
	public List<T> getAll();
	
	//分页查询
	public PageBean getPageBean(int currentPage,HQLUtils hqlUtils);

}

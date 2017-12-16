package com.cblue.oa.base;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Properties;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.cblue.oa.utils.HQLUtils;
import com.cblue.oa.utils.PageBean;

public class BaseDaoImpl<T> implements IBaseDao<T> {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private Class clazz;
	
	//得到当前类的父类的泛型的运行时类
	public BaseDaoImpl(){
		//获得当前类的父类的type
		ParameterizedType type = (ParameterizedType)this.getClass().getGenericSuperclass();
		//获得类的泛型
		Type types [] = type.getActualTypeArguments();
		//获得第一个泛型,并转化成Class类
		clazz = (Class<T>)types[0];
	}
	
	public Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	

	public void add(T entity) {
		// TODO Auto-generated method stub
		getSession().save(entity);
	}

	public void delete(Long id) {
		// TODO Auto-generated method stub
		getSession().delete(getSession().get(clazz, id));

	}

	public void update(T entity) {
		// TODO Auto-generated method stub
		getSession().update(entity);;
	}

	public T getById(Long id) {
		// TODO Auto-generated method stub
		return (T)getSession().get(clazz, id);
	}

	//from 实体名  t where t.id in(1,3,4)
	public List<T> getByIds(Long[] ids) {
		// TODO Auto-generated method stub
		String hql = "From " + clazz.getSimpleName() +" t where t.id in(:ids)";
		Query query = getSession().createQuery(hql);
		query.setParameterList("ids", ids);
		return query.list();
	}

	public List<T> getAll() {
		// TODO Auto-generated method stub
		//from 实体名
		String hql = "From " + clazz.getSimpleName() ;
		return getSession().createQuery(hql).list();
	}
	
	public PageBean getPageBean(int currentPage,HQLUtils hqlUtils) {
		// TODO Auto-generated method stub
		 int pageSize = getPageSizeByProperties();
		 int firstResult = (currentPage-1)*pageSize;		 
		  Query query = getSession().createQuery(hqlUtils.getHQL());
		  if(hqlUtils.getArgs()!=null&&hqlUtils.getArgs().size()>0){
			   int count =0;
			  for(Object o:hqlUtils.getArgs()){
				  query.setParameter(count++,o);
			  }
		  }
		  
		  List recordList = query.setFirstResult(firstResult).setMaxResults(pageSize).list();
	  
		  Query countQuery = getSession().createQuery(hqlUtils.getCountHQL());
		  if(hqlUtils.getArgs()!=null&&hqlUtils.getArgs().size()>0){
			   int count =0;
			  for(Object o:hqlUtils.getArgs()){
				  countQuery.setParameter(count++,o);
			  }
		  }
		  
		  Long count = (Long)countQuery.uniqueResult();
		  
		
		return new PageBean(currentPage, recordList, pageSize, count.intValue());
	}
	
	private int getPageSizeByProperties(){
		int pageSize = 10;
		
		Properties properties = new Properties();
		InputStream input = this.getClass().getClassLoader().getResourceAsStream("pageSize.properties");
		try {
			properties.load(input);
			String pageSizeStr = (String) properties.get("pagesize");
			pageSize = Integer.valueOf(pageSizeStr);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return pageSize;
	}

}

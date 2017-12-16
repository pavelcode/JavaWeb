package com.cblue.oa.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.cblue.oa.base.BaseDaoImpl;
import com.cblue.oa.dao.IReplyDao;
import com.cblue.oa.entity.Reply;
import com.cblue.oa.entity.Theme;
import com.cblue.oa.utils.HQLUtils;
import com.cblue.oa.utils.PageBean;

@Repository
public class ReplyDaoImpl extends BaseDaoImpl<Reply> implements IReplyDao {

	@SuppressWarnings("unchecked")
	public List<Reply> getReplyTopList(Theme model) {
		// TODO Auto-generated method stub
		String hql ="from Reply r where r.theme=? order by r.postTime";
		return getSession().createQuery(hql).setParameter(0, model).list();
	}

	/*public PageBean getPageBean(int currentPage, Theme model) {
		// TODO Auto-generated method stub
		 int pageSize = 2;
		 int firstResult = (currentPage-1)*pageSize;
		 String hql = "from Reply r where  r.theme=? order by r.postTime asc";
		 List recordList = getSession().createQuery(hql).setParameter(0,model).setFirstResult(firstResult).setMaxResults(pageSize).list();
		 
		 hql ="select count(*) from Reply r where  r.theme=?";
		 Long count = (Long)getSession().createQuery(hql).setParameter(0, model).uniqueResult();
		
		return new PageBean(currentPage, recordList, pageSize, count.intValue());
	}*/
	
	/*public PageBean getPageBean(int currentPage,HQLUtils hqlUtils) {
		// TODO Auto-generated method stub
		 int pageSize = 2;
		 int firstResult = (currentPage-1)*pageSize;
		
		 //String hql = "from Reply r where  r.theme=? order by r.postTime asc";

		
		// List recordList = getSession().createQuery(hqlUtils.getHQL()).setFirstResult(firstResult).setMaxResults(pageSize).list();
		 
		  Query query = getSession().createQuery(hqlUtils.getHQL());
		  if(hqlUtils.getArgs()!=null&&hqlUtils.getArgs().size()>0){
			   int count =0;
			  for(Object o:hqlUtils.getArgs()){
				  query.setParameter(count++,o);
			  }
		  }
		  
		  List recordList = query.setFirstResult(firstResult).setMaxResults(pageSize).list();
		 
		 //hql ="select count(*) from Reply r where  r.theme=?";
		
		 //Long count = (Long)getSession().createQuery(hqlUtils.getCountHQL()).uniqueResult();
		  
		  Query countQuery = getSession().createQuery(hqlUtils.getCountHQL());
		  if(hqlUtils.getArgs()!=null&&hqlUtils.getArgs().size()>0){
			   int count =0;
			  for(Object o:hqlUtils.getArgs()){
				  countQuery.setParameter(count++,o);
			  }
		  }
		  
		  Long count = (Long)countQuery.uniqueResult();
		  
		
		return new PageBean(currentPage, recordList, pageSize, count.intValue());
	}*/

	
}

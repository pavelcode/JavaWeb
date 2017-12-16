package com.cblue.oa.utils;

import java.util.ArrayList;
import java.util.List;

public class HQLUtils {
	
	/**
	 * 
	 *from Reply r 
	 *where  r.theme=? and r.aaa=?
	 *order by r.postTime asc
	 *
	 */
	
	private String fromStr ;
	private String whereStr="" ;
	private String orderStr="" ;
	
	private List<Object> args = new ArrayList<Object>();

	public String getFromStr() {
		return fromStr;
	}

	public void setFromStr(String fromStr) {
		this.fromStr = fromStr;
	}

	public String getWhereStr() {
		return whereStr;
	}

	public void setWhereStr(String whereStr) {
		this.whereStr = whereStr;
	}

	public String getOrderStr() {
		return orderStr;
	}

	public void setOrderStr(String orderStr) {
		this.orderStr = orderStr;
	}

	public List<Object> getArgs() {
		return args;
	}

	public void setArgs(List<Object> args) {
		this.args = args;
	}
	
	//拼装sql语句
	public HQLUtils(Class clazz){
		this.fromStr = " from " +clazz.getSimpleName()+" o ";
	}
	
	//拼装where语句 addWhere(r.theme=,theme)
	public void addWhere(String condition,Object...arguments){
		if(this.whereStr.length()==0){
			//第一次拼装where
			this.whereStr = " where "+condition;
		}else{
			//非第一次拼装where
			this.whereStr += " and "+condition;
		}
		//处理参数
		if(arguments!=null&&arguments.length>0){
			for(Object o: arguments){
				args.add(o);
			}
		}
	}
	
   //拼装order语句
	public void addOrder(String orderby,boolean asc){
		if(this.orderStr.length()==0){
			//第一次拼装order
			this.orderStr=" order by "+orderby + (asc?" asc ":" desc ");
		}else{
			//非第一次拼装order
			this.orderStr+=" , "+orderby + (asc?" asc ":" desc ");
		}
	}
	
	//获得HQL语句
	public String getHQL(){
		return this.fromStr + this.whereStr +this.orderStr;
	}
	
	//获得CountHQL语句
	public String getCountHQL(){
		return "select count(*) "+this.fromStr +this.whereStr;
	}
	
	
	

}

package com.cblue.oa.dao;

import java.util.List;

import com.cblue.oa.base.IBaseDao;
import com.cblue.oa.entity.Privilege;

public interface IPrivilegeDao extends IBaseDao<Privilege> {

	public List<Privilege> getTopList();

	public List<String> getAllUrl();

}

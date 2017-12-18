package com.cblue.oa.dao;

import java.util.List;

import com.cblue.oa.base.IBaseDao;
import com.cblue.oa.entity.Check;

public interface ICheckDao extends IBaseDao<Check> {

	public List<Check> getAllCheckById(Long id);

}

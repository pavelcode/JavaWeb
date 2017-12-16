package com.cblue.oa.service;

import java.util.List;

import com.cblue.oa.entity.Privilege;

public interface IPrivilegeService {

	public List<Privilege> getAll();

	public List<Privilege> getByIds(Long[] privilegeIds);

	public List<Privilege> getTopList();

	public List<String> getAllUrl();

}

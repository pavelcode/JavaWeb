package com.cblue.oa.service;

import java.util.List;

import com.cblue.oa.entity.Role;

public interface IRoleService {

	public List<Role> queryAll();

	public void save(Role model);

	public void delete(Role model);

	public Role getById(Role model);

	public void update(Role model);

	public List<Role> getByIds(Long[] roleIdList);


}

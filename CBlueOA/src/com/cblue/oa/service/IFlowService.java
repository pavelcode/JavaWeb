package com.cblue.oa.service;

import com.cblue.oa.entity.Apply;
import com.cblue.oa.utils.HQLUtils;
import com.cblue.oa.utils.PageBean;

public interface IFlowService {

	public void submit(Apply apply);

	public PageBean getPageBean(HQLUtils hqlUtils, int currentPage);

}

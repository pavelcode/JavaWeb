package com.cblue.oa.service;

import java.util.List;

import com.cblue.oa.entity.Apply;
import com.cblue.oa.entity.Check;
import com.cblue.oa.entity.TaskView;
import com.cblue.oa.entity.User;
import com.cblue.oa.utils.HQLUtils;
import com.cblue.oa.utils.PageBean;

public interface IFlowService {

	public void submit(Apply apply);

	public PageBean getPageBean(HQLUtils hqlUtils, int currentPage);

	public Apply getApplyById(Long applyId);

	public List<Check> getAllCheckById(Long applyId);

	public List<TaskView> getMyCheckTaskList(User loginUser);

	public void check(Check check, String taskId);



}

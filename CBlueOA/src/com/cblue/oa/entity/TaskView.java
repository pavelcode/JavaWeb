package com.cblue.oa.entity;

import org.jbpm.api.task.Task;

public class TaskView {

	private Apply apply;
	
	private Task task;

	public TaskView() {
		super();
	}

	public TaskView(Apply apply, Task task) {
		super();
		this.apply = apply;
		this.task = task;
	}

	public Apply getApply() {
		return apply;
	}

	public void setApply(Apply apply) {
		this.apply = apply;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}
	
	
}

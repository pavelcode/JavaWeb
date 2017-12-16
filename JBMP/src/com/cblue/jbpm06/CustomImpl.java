package com.cblue.jbpm06;

import java.util.Map;

import org.jbpm.api.activity.ActivityExecution;
import org.jbpm.api.activity.ExternalActivityBehaviour;

public class CustomImpl implements ExternalActivityBehaviour {

	@Override
	public void execute(ActivityExecution execution) throws Exception {
		// TODO Auto-generated method stub
		execution.waitForSignal();
        System.out.println("execute");
	}

	@Override
	public void signal(ActivityExecution arg0, String arg1, Map<String, ?> arg2)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("signal");

	}

}

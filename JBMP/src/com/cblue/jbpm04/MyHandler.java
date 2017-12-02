package com.cblue.jbpm04;

import org.jbpm.api.jpdl.DecisionHandler;
import org.jbpm.api.model.OpenExecution;

public class MyHandler implements DecisionHandler {

	/**
	 * @return 判断之后，下一步的路径名
	 */
	@Override
	public String decide(OpenExecution exection) {
		String name ="不超过300";
		//通过变量获得花的钱
		Double money = (Double)exection.getVariable("money");
		if(money>300){
			name="超过300";
		}
		
		return name;
	}

}

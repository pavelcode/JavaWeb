package com.cblue.jbpm07;

import org.jbpm.api.listener.EventListener;
import org.jbpm.api.listener.EventListenerExecution;

public class MyEvent implements EventListener {

	@Override
	public void notify(EventListenerExecution arg0) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("我的事件执行了");
	}

}

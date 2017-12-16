package com.cblue.oa.reflect;

import org.junit.Test;

public class Test01 {

	@Test
	public void test01(){
		//java.util.Date
		Class clazz = java.util.Date.class;
		System.out.println(clazz.getSimpleName());
	}
	
}

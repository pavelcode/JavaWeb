package com.cblue.maven;

import org.junit.Test;
public class TestHelloMaven{

	@Test
	public void testHelloMaven(){
		HelloMaven helloMaven = new HelloMaven();
		String result = helloMaven.sayHello();
	    assert result=="hello maven";
	}
}
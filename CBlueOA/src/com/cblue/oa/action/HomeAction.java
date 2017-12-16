package com.cblue.oa.action;

import org.springframework.stereotype.Controller;

@Controller
public class HomeAction {
	
	
	public String index(){
		return "index";
	}
	
	public String header(){
		return "header";
	}
	
	public String footer(){
		return "footer";
	}
	
	public String left(){
		return "left";
	}
	
	public String right(){
		return "right";
	}

}

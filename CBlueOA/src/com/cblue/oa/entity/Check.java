package com.cblue.oa.entity;

import java.util.Date;
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Untitled
//  @ File Name : Check.java
//  @ Date : 2017/12/15
//  @ Author : 
//
//




public class Check {
	
	
	
	private Long id;
	private User checkUser;
	private Date checkDate;
	private Boolean isPass;
	private String checkIdea;
	private Apply apply;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public User getCheckUser() {
		return checkUser;
	}
	public void setCheckUser(User checkUser) {
		this.checkUser = checkUser;
	}
	public Date getCheckDate() {
		return checkDate;
	}
	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}
	public Boolean getIsPass() {
		return isPass;
	}
	public void setIsPass(Boolean isPass) {
		this.isPass = isPass;
	}
	public String getCheckIdea() {
		return checkIdea;
	}
	public void setCheckIdea(String checkIdea) {
		this.checkIdea = checkIdea;
	}
	public Apply getApply() {
		return apply;
	}
	public void setApply(Apply apply) {
		this.apply = apply;
	}
	
	
}
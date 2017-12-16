package com.cblue.oa.dao.impl;

import org.springframework.stereotype.Repository;

import com.cblue.oa.base.BaseDaoImpl;
import com.cblue.oa.dao.IStudentDao;
import com.cblue.oa.entity.Student;

//得到当前类的父类的泛型的运行时类
@Repository
public class StudentDaoImpl extends BaseDaoImpl<Student> implements IStudentDao {

}

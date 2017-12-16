package com.cblue.oa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cblue.oa.dao.IStudentDao;
import com.cblue.oa.entity.Student;
import com.cblue.oa.service.IStudentService;

@Service
@Transactional
public class StudentServiceImpl implements IStudentService {

	@Autowired
	private IStudentDao studentDao;
	
	public void add(Student entity) {
		// TODO Auto-generated method stub
		studentDao.add(entity);

	}

	public void delete(Long id) {
		// TODO Auto-generated method stub
		studentDao.delete(id);

	}

	public void update(Student entity) {
		// TODO Auto-generated method stub
		studentDao.update(entity);

	}

	public Student getById(Long id) {
		// TODO Auto-generated method stub
		return studentDao.getById(id);
	}

	public List<Student> getByIds(Long[] ids) {
		// TODO Auto-generated method stub
		return studentDao.getByIds(ids);
	}

	public List<Student> getAll() {
		// TODO Auto-generated method stub
		return studentDao.getAll();
	}

}

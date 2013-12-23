package com.mybatis3.services;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mybatis3.domain.Student;
import com.mybatis3.mappers.StudentMapper;
import com.mybatis3.util.MybatisSqlSessionFactory;

public class StudentService {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	public List<Student> findAllStudents() {
		SqlSession sqlSession = MybatisSqlSessionFactory.openSession(); //
		
		try{
			StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
			return studentMapper.findAllStudents();
		} finally {
			sqlSession.close();
		}
	}
	
	public Student findStudentById(Integer studId) {
		logger.debug("select Student By ID :{}", studId); 
		SqlSession sqlSession = MybatisSqlSessionFactory.openSession();
		try {
			StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
			return studentMapper.findStudentById(studId);
		} finally {
			sqlSession.close();
		}
	}
	
	public void createStudent(Student student) {

		SqlSession sqlSession = MybatisSqlSessionFactory.openSession();
		try {
			StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
			studentMapper.insertStudent(student);
			sqlSession.commit();
		} finally {
			sqlSession.close();
		}
	}

}

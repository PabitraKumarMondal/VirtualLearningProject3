package com.cognizant.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cognizant.model.Student;

@Repository
@Qualifier("StudentDao")
public class StudentDaoImpl implements StudentDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public String insert(Student s) {
		int row = jdbcTemplate.update("INSERT INTO student VALUES(?, ?, ?)", s.getId(), s.getName(), s.getMarks());
		if (row > 0)
			return "SUCCESS";
		return "FAIL";
	}

	public String update(Student student) {
		int row = jdbcTemplate.update("UPDATE student SET name=?, marks=? WHERE id=?", student.getName(), student.getMarks(), student.getId());
		if (row > 0)
			return "SUCCESS";
		return "FAIL";
	}
	
	public String delete(Student student) {
		int row = jdbcTemplate.update("DELETE FROM student WHERE id=?", student.getId());
		if (row > 0)
			return "SUCCESS";
		return "FAIL";	
	}
	public List<Student> getAll() {
		List<Student> students = jdbcTemplate.query("SELECT * FROM student", 
				(rs,rowNum) -> new Student(rs.getInt(1),rs.getString(2),rs.getInt(3))
				);
		return students;
	}


}
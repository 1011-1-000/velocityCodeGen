package com.generate.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import com.vel.noparse.Student;


public class StudentDao{
	
	private Map<Integer,Student> students = new HashMap<Integer,Student>();
	private Integer maxId = 0;
	public StudentDao() {
		Student s1 = new Student();
		Student s2 = new Student();
		Student s3 = new Student();
		Student s4 = new Student();
		Student s5 = new Student();
		Student s6 = new Student();
		Student s7 = new Student();
		Student s8 = new Student();
		students.put(1, s1);
		students.put(2, s2);
		students.put(3, s3);
		students.put(4, s4);
		students.put(5, s5);
		students.put(6, s6);
		students.put(7, s7);
		students.put(8, s8);
		maxId = 8;
	}

	public List<Student> retrieveAllStudents() {
		List<Student> stuList = new ArrayList<Student>();
		Iterator<Entry<Integer, Student>> iterator = students.entrySet().iterator();
		while(iterator.hasNext()) {
			Entry<Integer, Student> entry = iterator.next();
			stuList.add(entry.getValue());
		}
		return stuList;
	}

	public Student retrieveById(Integer id) {
		return students.get(id);
	}

	public void update(Student student) {
		//students.remove(student.getId());
		students.put(student.getId(), student);
	}

	public void add(Student student) {
		maxId ++;
		student.setId(maxId);
		students.put(maxId, student);
	}

	public void delete(Integer id) {
		students.remove(id);
	}
}

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
		Student s1 = new Student(1,"s1",11,101,101);
		Student s2 = new Student(2,"s2",12,102,102);
		Student s3 = new Student(3,"s3",13,103,103);
		Student s4 = new Student(4,"s4",14,104,104);
		Student s5 = new Student(5,"s5",15,105,105);
		Student s6 = new Student(6,"s6",16,106,106);
		Student s7 = new Student(7,"s7",17,107,107);
		Student s8 = new Student(8,"s8",18,108,108);
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

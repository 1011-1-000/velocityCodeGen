package com.generate.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import com.vel.noparse.Teacher;


public class TeacherDao{
	
	private Map<Integer,Teacher> teachers = new HashMap<Integer,Teacher>();
	private Integer maxId = 0;
	public TeacherDao() {
		Teacher s1 = new Teacher();
		Teacher s2 = new Teacher();
		Teacher s3 = new Teacher();
		Teacher s4 = new Teacher();
		Teacher s5 = new Teacher();
		Teacher s6 = new Teacher();
		Teacher s7 = new Teacher();
		Teacher s8 = new Teacher();
		teachers.put(1, s1);
		teachers.put(2, s2);
		teachers.put(3, s3);
		teachers.put(4, s4);
		teachers.put(5, s5);
		teachers.put(6, s6);
		teachers.put(7, s7);
		teachers.put(8, s8);
		maxId = 8;
	}

	public List<Teacher> retrieveAllTeachers() {
		List<Teacher> stuList = new ArrayList<Teacher>();
		Iterator<Entry<Integer, Teacher>> iterator = teachers.entrySet().iterator();
		while(iterator.hasNext()) {
			Entry<Integer, Teacher> entry = iterator.next();
			stuList.add(entry.getValue());
		}
		return stuList;
	}

	public Teacher retrieveById(Integer id) {
		return teachers.get(id);
	}

	public void update(Teacher teacher) {
		//teachers.remove(teacher.getId());
		teachers.put(teacher.getId(), teacher);
	}

	public void add(Teacher teacher) {
		maxId ++;
		teacher.setId(maxId);
		teachers.put(maxId, teacher);
	}

	public void delete(Integer id) {
		teachers.remove(id);
	}
}

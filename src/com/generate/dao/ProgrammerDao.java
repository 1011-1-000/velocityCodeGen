package com.generate.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import com.vel.noparse.Programmer;


public class ProgrammerDao{
	
	private Map<Integer,Programmer> programmers = new HashMap<Integer,Programmer>();
	private Integer maxId = 0;
	public ProgrammerDao() {
		Programmer s1 = new Programmer();
		Programmer s2 = new Programmer();
		Programmer s3 = new Programmer();
		Programmer s4 = new Programmer();
		Programmer s5 = new Programmer();
		Programmer s6 = new Programmer();
		Programmer s7 = new Programmer();
		Programmer s8 = new Programmer();
		programmers.put(1, s1);
		programmers.put(2, s2);
		programmers.put(3, s3);
		programmers.put(4, s4);
		programmers.put(5, s5);
		programmers.put(6, s6);
		programmers.put(7, s7);
		programmers.put(8, s8);
		maxId = 8;
	}

	public List<Programmer> retrieveAllProgrammers() {
		List<Programmer> stuList = new ArrayList<Programmer>();
		Iterator<Entry<Integer, Programmer>> iterator = programmers.entrySet().iterator();
		while(iterator.hasNext()) {
			Entry<Integer, Programmer> entry = iterator.next();
			stuList.add(entry.getValue());
		}
		return stuList;
	}

	public Programmer retrieveById(Integer id) {
		return programmers.get(id);
	}

	public void update(Programmer programmer) {
		//programmers.remove(programmer.getId());
		programmers.put(programmer.getId(), programmer);
	}

	public void add(Programmer programmer) {
		maxId ++;
		programmer.setId(maxId);
		programmers.put(maxId, programmer);
	}

	public void delete(Integer id) {
		programmers.remove(id);
	}
}

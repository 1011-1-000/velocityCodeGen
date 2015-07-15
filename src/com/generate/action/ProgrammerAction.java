
package com.generate.action;
import com.vel.noparse.Programmer;
import java.util.List;
import com.generate.dao.ProgrammerDao;

public class ProgrammerAction{
	public ProgrammerDao programmerDao;
	private List<Programmer> programmers;
	private Programmer programmer;
	private String work;
	private Integer id;
	private String school;
	private Integer age;
	private String name;
	private String lanauge;
	public String programmerList() {
		programmers = programmerDao.retrieveAllProgrammers();
		return "programmerList.jsp";
	}
	
	public String toProgrammerModify() {
		programmer = programmerDao.retrieveById(id);
		return "programmerModify.jsp";
	}
	public void programmerModify(Programmer programmer) {
		programmerDao.update(programmer);
	}
	
	public String toProgrammerAdd() {
		return "programmerAdd.jsp";
	}
	public void programmerAdd(Programmer programmer) {
		programmerDao.add(programmer);
	}
	public void programmerDelete() {
		programmerDao.delete(id);
	}
	
	public List<Programmer> getProgrammers() {
		return programmers;
	}
	public void setProgrammers(List<Programmer> programmers) {
		this.programmers = programmers;
	}
	
	public Programmer getProgrammer() {
		return programmer;
	}

	public void setProgrammer(Programmer programmer) {
		this.programmer = programmer;
	}

	public String getWork() {
		return work;
	}
	public void setWork(String work) {
		this.work = work;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLanauge() {
		return lanauge;
	}
	public void setLanauge(String lanauge) {
		this.lanauge = lanauge;
	}

}

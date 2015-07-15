
package com.generate.action;
import com.vel.noparse.Teacher;
import java.util.List;
import com.generate.dao.TeacherDao;

public class TeacherAction{
	public TeacherDao teacherDao;
	private List<Teacher> teachers;
	private Teacher teacher;
	private Integer id;
	private String serializeNo;
	private String subject;
	private String titile;
	private String name;
	public String teacherList() {
		teachers = teacherDao.retrieveAllTeachers();
		return "teacherList.jsp";
	}
	
	public String toTeacherModify() {
		teacher = teacherDao.retrieveById(id);
		return "teacherModify.jsp";
	}
	public void teacherModify(Teacher teacher) {
		teacherDao.update(teacher);
	}
	
	public String toTeacherAdd() {
		return "teacherAdd.jsp";
	}
	public void teacherAdd(Teacher teacher) {
		teacherDao.add(teacher);
	}
	public void teacherDelete() {
		teacherDao.delete(id);
	}
	
	public List<Teacher> getTeachers() {
		return teachers;
	}
	public void setTeachers(List<Teacher> teachers) {
		this.teachers = teachers;
	}
	
	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSerializeNo() {
		return serializeNo;
	}
	public void setSerializeNo(String serializeNo) {
		this.serializeNo = serializeNo;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getTitile() {
		return titile;
	}
	public void setTitile(String titile) {
		this.titile = titile;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}

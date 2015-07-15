
package com.generate.action;
import com.vel.noparse.Student;
import java.util.List;
import com.generate.dao.StudentDao;

public class StudentAction{
	public StudentDao studentDao;
	private List<Student> students;
	private Student student;
	private Integer id;
	private Integer weight;
	private Integer height;
	private Integer age;
	private String name;
	public String studentList() {
		students = studentDao.retrieveAllStudents();
		return "studentList.jsp";
	}
	
	public String toStudentModify() {
		student = studentDao.retrieveById(id);
		return "studentModify.jsp";
	}
	public void studentModify(Student student) {
		studentDao.update(student);
	}
	
	public String toStudentAdd() {
		return "studentAdd.jsp";
	}
	public void studentAdd(Student student) {
		studentDao.add(student);
	}
	public void studentDelete() {
		studentDao.delete(id);
	}
	
	public List<Student> getStudents() {
		return students;
	}
	public void setStudents(List<Student> students) {
		this.students = students;
	}
	
	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getWeight() {
		return weight;
	}
	public void setWeight(Integer weight) {
		this.weight = weight;
	}
	public Integer getHeight() {
		return height;
	}
	public void setHeight(Integer height) {
		this.height = height;
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

}

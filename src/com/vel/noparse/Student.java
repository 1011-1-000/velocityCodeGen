package com.vel.noparse;

public class Student implements java.io.Serializable{

	private static final long serialVersionUID = 824958769696475850L;
	private Integer id;
	private String name;
	private Integer age;
	private Integer height;
	private Integer weight;
	
	public Student() {
		super();
	}
	public Student(Integer id, String name, Integer age, Integer height,
			Integer weight) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.height = height;
		this.weight = weight;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Integer getHeight() {
		return height;
	}
	public void setHeight(Integer height) {
		this.height = height;
	}
	public Integer getWeight() {
		return weight;
	}
	public void setWeight(Integer weight) {
		this.weight = weight;
	}
}

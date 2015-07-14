package com.vel.noparse;

public class Teacher implements java.io.Serializable{

	private static final long serialVersionUID = 7749420452177655540L;
	private Integer id;
	private String name;
	private String serializeNo;
	private String titile;
	private String subject;
	
	public Teacher() {
		super();
	}
	public Teacher(Integer id, String name, String serializeNo, String titile,
			String subject) {
		super();
		this.id = id;
		this.name = name;
		this.serializeNo = serializeNo;
		this.titile = titile;
		this.subject = subject;
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
	public String getSerializeNo() {
		return serializeNo;
	}
	public void setSerializeNo(String serializeNo) {
		this.serializeNo = serializeNo;
	}
	public String getTitile() {
		return titile;
	}
	public void setTitile(String titile) {
		this.titile = titile;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
}

package com.lwl.domain;

public class User {

	private long id;
	private String name;
	private String sex;
	private String job;
	
	public User() {
	}
	
	public User(String name,String sex,String job) {
		this.name = name;
		this.sex = sex;
		this.job = job;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", sex=" + sex + ", job=" + job + "]";
	}
	
	
}

package com.wellwisher.consumer.pojo;

public class People {

	private int id;
	private String name;
	private String email;
	private String occasion;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getOccasion() {
		return occasion;
	}
	public void setOccasion(String occasion) {
		this.occasion = occasion;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public People() {
		super();
	}
	public People(int id, String name, String email, String occasion) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.occasion = occasion;
	}
}


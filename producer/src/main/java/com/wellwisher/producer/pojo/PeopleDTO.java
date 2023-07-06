package com.wellwisher.producer.pojo;

public class PeopleDTO {

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
	public void setEmailId(String emailId) {
		this.email = emailId;
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
	public PeopleDTO() {
		super();
	}
	public PeopleDTO(int id, String name, String emailId, String occasion) {
		super();
		this.id = id;
		this.name = name;
		this.email = emailId;
		this.occasion = occasion;
	}
	
	public PeopleDTO(People people)
	{
		super();
		this.id = people.getId();
		this.name = people.getName();
		this.email = people.getEmail();
		this.occasion = people.getOccasion();
	}
	
	
}

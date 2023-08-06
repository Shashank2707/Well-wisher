package com.wellwisher.producer.pojo;

import java.time.LocalDate;

import com.wellwisher.producer.entity.PeopleEntity;

public class PeopleRequest {
	
	private String name;
	private String email;
	private String occasion;
	private LocalDate occasionDate;
	
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
	public LocalDate getOccasionDate() {
		return occasionDate;
	}
	public void setOccasionDate(LocalDate occasionDate) {
		this.occasionDate = occasionDate;
	}
	public PeopleRequest() {
		super();
	}
    
	public PeopleRequest (PeopleEntity peopleEntity)
	{
		super();
		this.name = peopleEntity.getName();
		this.email = peopleEntity.getEmail();
		this.occasion = peopleEntity.getOccasion();
		this.occasionDate = peopleEntity.getDate();
	}
	
	
}

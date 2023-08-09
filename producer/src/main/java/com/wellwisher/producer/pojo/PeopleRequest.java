package com.wellwisher.producer.pojo;

import java.time.LocalDate;

import com.wellwisher.producer.entity.PeopleEntity;
import com.wellwisher.producer.util.WellWisherStringUtils;

public class PeopleRequest {
	
	private String name;
	private String email;
	private String occasion;
	private LocalDate occasionDate;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = WellWisherStringUtils.toTitleCase(name);
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email.toLowerCase();
	}
	public String getOccasion() {
		return occasion;
	}
	public void setOccasion(String occasion) {
		this.occasion = WellWisherStringUtils.toTitleCase(occasion);
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
		this.setName(peopleEntity.getName());
		this.setEmail(peopleEntity.getEmail());
		this.setOccasion(peopleEntity.getOccasion());
		this.setOccasionDate(peopleEntity.getDate());
	}
}

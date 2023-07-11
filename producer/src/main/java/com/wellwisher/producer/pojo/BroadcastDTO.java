package com.wellwisher.producer.pojo;

import com.wellwisher.producer.entity.BroadcastEntity;
import com.wellwisher.producer.entity.PeopleEntity;

public class BroadcastDTO {

	private int id;
	private String name;
	private String email;
	private String occasion;
	private String template;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
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
	public String getTemplate() {
		return template;
	}
	public void setTemplate(String template) {
		this.template = template;
	}
	public BroadcastDTO() {
		super();
	}
	
	public BroadcastDTO(BroadcastEntity be, PeopleEntity pe) {
		super();
		this.email = pe.getEmail();
		this.id = pe.getId();
		this.name = pe.getName();
		this.occasion = be.getOccasion();
		this.template = be.getTemplate();
	}
}

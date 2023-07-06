package com.wellwisher.consumer.pojo;


public class People{

	private int id;
	private String name;
	private String nickName; 
	private String email;
	private String date;
	public People() {
	}
	public People(int id, String name, String nickName, String email, String date, String occasion,
			boolean subscription) {

		this.id = id;
		this.name = name;
		this.nickName = nickName;
		this.email = email;
		this.date = date;
		this.occasion = occasion;
		this.subscription = subscription;
	}
	private String occasion;
	private boolean subscription;
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
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getOccasion() {
		return occasion;
	}
	public void setOccasion(String occasion) {
		this.occasion = occasion;
	}
	public boolean isSubscription() {
		return subscription;
	}
	public void setSubscription(boolean subscription) {
		this.subscription = subscription;
	}
	
}



package com.wellwisher.producer.service;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.wellwisher.producer.entity.BroadcastEntity;
import com.wellwisher.producer.entity.PeopleEntity;
import com.wellwisher.producer.pojo.PeopleRequest;

public interface WellwisherService {
	public List<PeopleEntity> getAllPeoples();
	public ResponseEntity<String> subscribe(PeopleRequest peopleDto);
	public List<PeopleEntity> getByDate();
	public List<BroadcastEntity> getBroadcastOccasion();
	public ResponseEntity<String> unsubscribe(String email);
}

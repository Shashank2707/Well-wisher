package com.wellwisher.producer.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.wellwisher.producer.entity.BroadcastEntity;
import com.wellwisher.producer.entity.PeopleEntity;
import com.wellwisher.producer.pojo.PeopleRequest;
import com.wellwisher.producer.repository.BroadcastDAO;
import com.wellwisher.producer.repository.WellwisherDAO;
import com.wellwisher.producer.util.DateUtil;

@Service
public class WellwisherServiceIMPL implements WellwisherService{

	@Autowired
	WellwisherDAO wellWisherDAO;
	
	@Autowired
	BroadcastDAO broadcastDAO;
	
	@Override
	public ResponseEntity<String> subscribe(PeopleRequest peopleDto) {
		PeopleEntity people = new PeopleEntity(peopleDto);
		Optional<PeopleEntity> select = wellWisherDAO.findByEmailAndOccasion(people.getEmail(), people.getOccasion());
		if (select.isPresent()) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Similar entry already exists!");
		}
		wellWisherDAO.save(people);
		return ResponseEntity.ok("Saved");
	}
	
	@Override
	public List<PeopleEntity> getAllPeoples() {
		return wellWisherDAO.findAll();
	}
	
	@Override
	public List<PeopleEntity> getByDate() {
		LocalDate istDate = DateUtil.getTodaysZoneDate();
		return wellWisherDAO.findAllByDateToday(istDate.getDayOfMonth(), istDate.getMonthValue());
	}
	
	@Override
	public List<BroadcastEntity> getBroadcastOccasion() {
		LocalDate istDate = DateUtil.getTodaysZoneDate();
		return broadcastDAO.findAllByDate(istDate);
	}

}

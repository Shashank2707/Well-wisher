package com.wellwisher.producer.service;

import static com.wellwisher.producer.constants.Constants.SAVED_DATA;
import static com.wellwisher.producer.constants.Constants.SIMILAR_ENTRY_EXIST;
import static com.wellwisher.producer.constants.Constants.UNSUBSCRIBED;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException.InternalServerError;

import com.wellwisher.producer.entity.BroadcastEntity;
import com.wellwisher.producer.entity.PeopleEntity;
import com.wellwisher.producer.pojo.PeopleRequest;
import com.wellwisher.producer.repository.BroadcastDAO;
import com.wellwisher.producer.repository.WellwisherDAO;
import com.wellwisher.producer.util.DateUtil;
import com.wellwisher.producer.util.TotpUtil;
@Service
public class WellwisherServiceIMPL implements WellwisherService{

	@Autowired
	WellwisherDAO wellWisherDAO;
	
	@Autowired
	BroadcastDAO broadcastDAO;
	
	@Value("${wellwisher.secret.key}")
	private String secretKey;
	
	@Override
	public ResponseEntity<String> subscribe(PeopleRequest peopleDto) {
		PeopleEntity people = new PeopleEntity(peopleDto);
		Optional<PeopleEntity> select = wellWisherDAO.findByEmailAndOccasion(people.getEmail(), people.getOccasion());
		if (select.isPresent()) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(SIMILAR_ENTRY_EXIST);
		}
		wellWisherDAO.save(people);
		return ResponseEntity.ok(SAVED_DATA);
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

	@Override
	public ResponseEntity<String> unsubscribe(String email) {
		wellWisherDAO.unsubscribe(email);
		return ResponseEntity.ok(UNSUBSCRIBED);
	}

	@Override
	public ResponseEntity<List<PeopleRequest>> getAllUser(String otp) {
		if(TotpUtil.getTOTPCode(secretKey).equals(otp)) {
			
		List<PeopleEntity> customList = wellWisherDAO.findAll();		
		List<PeopleRequest> allUser = customList.stream()
		        .map(PeopleRequest::new)
		        .collect(Collectors.toList());
		return ResponseEntity.ok(allUser);
	 }
	  return new ResponseEntity<>(Collections.emptyList(),HttpStatus.UNAUTHORIZED);
	}
}

package com.wellwisher.producer.service;

import static com.wellwisher.producer.constants.Constants.SAVED_DATA;
import static com.wellwisher.producer.constants.Constants.SIMILAR_ENTRY_EXIST;
import static com.wellwisher.producer.constants.Constants.UNSUBSCRIBED;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.wellwisher.producer.entity.BroadcastEntity;
import com.wellwisher.producer.entity.PeopleEntity;
import com.wellwisher.producer.pojo.PeopleRequest;
import com.wellwisher.producer.repository.BroadcastDAO;
import com.wellwisher.producer.repository.WellwisherDAO;
import com.wellwisher.producer.util.DateUtil;
import com.wellwisher.producer.util.TotpUtil;

@Service
public class WellwisherServiceIMPL implements WellwisherService {

	@Autowired
	WellwisherDAO wellWisherDAO;

	@Autowired
	BroadcastDAO broadcastDAO;

	@Value("${wellwisher.secret.key}")
	private String secretKey;

	@Override
	public ResponseEntity<String> subscribe(PeopleRequest peopleRequest) {
		Optional<PeopleEntity> select = wellWisherDAO.findByEmailAndOccasion(peopleRequest.getEmail(), peopleRequest.getOccasion());
		if (select.isPresent()) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(SIMILAR_ENTRY_EXIST);
		}
		wellWisherDAO.save(new PeopleEntity(peopleRequest));
		select = wellWisherDAO.findByEmailAndOccasion(peopleRequest.getEmail(), peopleRequest.getOccasion());
		if (select.isPresent()) {
			return ResponseEntity.ok(SAVED_DATA);
		}
		// throw new Internal Server Error
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Entry not created!");
	}

	@Override
	public ResponseEntity<String> unsubscribe(String email) {
		int updateCount = wellWisherDAO.unsubscribe(email);
		if (updateCount > 0)
			return ResponseEntity.ok(UNSUBSCRIBED);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email Id not found");
	}

	@Override
	public ResponseEntity<List<PeopleRequest>> getAllUser(String otp) {
		if (TotpUtil.validate(secretKey, otp.trim())) {
			List<PeopleEntity> customList = wellWisherDAO.findAll();
			List<PeopleRequest> allUser = customList.stream()
					.map(PeopleRequest::new)
					.collect(Collectors.toList());
			return ResponseEntity.ok(allUser);
		}
		return new ResponseEntity<>(Collections.emptyList(), HttpStatus.UNAUTHORIZED);
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

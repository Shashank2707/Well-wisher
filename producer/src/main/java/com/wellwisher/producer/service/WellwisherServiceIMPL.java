package com.wellwisher.producer.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wellwisher.producer.pojo.People;
import com.wellwisher.producer.repository.WellwisherDAO;

@Service
public class WellwisherServiceIMPL implements WellwisherService{

	@Autowired
	WellwisherDAO wellWisherDAO;
	
	@Override
	public People subscribe(People people) {
		return wellWisherDAO.save(people);
	}
	
	@Override
	public List<People> get() {
		return wellWisherDAO.findAllByDate(LocalDate.now());
	}

}

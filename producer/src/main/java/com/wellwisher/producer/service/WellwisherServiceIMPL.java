package com.wellwisher.producer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wellwisher.producer.pojo.People;
import com.wellwisher.producer.repository.WellwisherDAO;

@Service
public class WellwisherServiceIMPL implements WellwisherService{

	@Autowired
	WellwisherDAO wellWisherDAO;
	
	public People subscribe(People people) {
		return wellWisherDAO.save(people);
	}

}

package com.wellwisher.producer.service;
import java.util.List;

import com.wellwisher.producer.pojo.People;

public interface WellwisherService {
	public People subscribe(People people);
	public List<People> get();
}

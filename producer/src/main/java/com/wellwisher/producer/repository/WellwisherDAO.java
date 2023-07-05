package com.wellwisher.producer.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wellwisher.producer.pojo.People;

@Repository
public interface WellwisherDAO extends JpaRepository<People, Integer> {

	public People save(People people);
	public List<People> findAllByDate(LocalDate date);
}

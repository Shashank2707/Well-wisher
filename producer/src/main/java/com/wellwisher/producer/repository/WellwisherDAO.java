package com.wellwisher.producer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.wellwisher.producer.pojo.People;

@Repository
public interface WellwisherDAO extends JpaRepository<People, Integer> {

	String query = "SELECT * FROM people p"
			+ " WHERE EXTRACT(DAY FROM p.date) = ?1"
			+ " AND EXTRACT(MONTH FROM p.date) = ?2";

	public People save(People people);
	@Query(value = query, nativeQuery = true)
	public List<People> findAllByDateToday(Integer date, Integer month);
}

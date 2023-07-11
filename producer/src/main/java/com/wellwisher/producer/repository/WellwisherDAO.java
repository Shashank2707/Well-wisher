package com.wellwisher.producer.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.wellwisher.producer.entity.PeopleEntity;

@Repository
public interface WellwisherDAO extends JpaRepository<PeopleEntity, Integer> {

	String query = "SELECT * FROM people p"
			+ " WHERE EXTRACT(DAY FROM p.date) = ?1"
			+ " AND EXTRACT(MONTH FROM p.date) = ?2";
	
	public PeopleEntity save(PeopleEntity people);
	@Query(value = query, nativeQuery = true)
	public List<PeopleEntity> findAllByDateToday(Integer date, Integer month);
	public Optional<PeopleEntity> findByEmailAndOccasion(String email, String occasion);
}

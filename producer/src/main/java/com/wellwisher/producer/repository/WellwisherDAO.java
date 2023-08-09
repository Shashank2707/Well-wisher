package com.wellwisher.producer.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.wellwisher.producer.entity.PeopleEntity;

@Repository
public interface WellwisherDAO extends JpaRepository<PeopleEntity, Integer> {

	final String allWithSameDateQuery = "SELECT * FROM people_entity p"
			+ " WHERE EXTRACT(DAY FROM p.date) = ?1"
			+ " AND EXTRACT(MONTH FROM p.date) = ?2";
	
	public PeopleEntity save(PeopleEntity people);
	
	@Query(value = allWithSameDateQuery, nativeQuery = true)
	public List<PeopleEntity> findAllByDateToday(Integer date, Integer month);
	
	public Optional<PeopleEntity> findByEmailAndOccasion(String email, String occasion);

	@Modifying
	@Transactional
	@Query(value = "UPDATE PeopleEntity SET SUBSCRIPTION = false WHERE EMAIL =:email")
	public int unsubscribe(@Param("email") String email);
}

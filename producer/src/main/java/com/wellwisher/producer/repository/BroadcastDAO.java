package com.wellwisher.producer.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wellwisher.producer.entity.BroadcastEntity;

public interface BroadcastDAO extends JpaRepository<BroadcastEntity, Integer>{

	public List<BroadcastEntity> findAllByDate(LocalDate date);
}

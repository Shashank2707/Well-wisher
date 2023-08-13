package com.wellwisher.producer.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.wellwisher.producer.entity.BroadcastEntity;

@DataJpaTest
class BroadcastDAOTest {
	
	@Autowired
	private BroadcastDAO underTest;
	
	void setUp() {
		BroadcastEntity entity = new BroadcastEntity();
		entity.setOccasion("Holi");
		entity.setDate(LocalDate.of(2023, 3, 8));
		entity.setTemplate("Holi2023");
		underTest.save(entity);
		
		BroadcastEntity entity2 = new BroadcastEntity();
		entity2.setOccasion("Eid");
		entity2.setDate(LocalDate.of(2023, 7, 1));
		entity2.setTemplate("Eid2023");
		underTest.save(entity2);
		
		BroadcastEntity entity3 = new BroadcastEntity();
		entity3.setOccasion("Diwali");
		entity3.setDate(LocalDate.of(2023, 11, 12));
		entity3.setTemplate("Diwali2023");
		underTest.save(entity3);
		
		BroadcastEntity entity4 = new BroadcastEntity();
		entity4.setOccasion("Event");
		entity4.setDate(LocalDate.of(2022, 11, 12));
		entity4.setTemplate("Event2023");
		underTest.save(entity4);
	}
	
	@Test
	void findEvent() {
		setUp();
		List<BroadcastEntity> l = underTest.findAllByDate(LocalDate.of(2023, 11, 12));
		assertEquals(1, l.size());
		assertEquals("Diwali", l.get(0).getOccasion());
		assertEquals("Diwali2023", l.get(0).getTemplate());
		assertEquals(LocalDate.of(2023, 11, 12), l.get(0).getDate());
	}

}

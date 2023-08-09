package com.wellwisher.producer.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.wellwisher.producer.entity.PeopleEntity;

@DataJpaTest
class WellwisherDAOTest {

    @Autowired
    private WellwisherDAO underTest;

    private void setUp() {
        String[] names = { "Utkarsh", "Shashank", "Divyansh", "Vijay", "Anurag", "Vaibhav", "utalmighty", "Utkarsh" };
        int[] years = { 1998, 2000, 1999, 1997, 1999, 1999, 2000, 1999 };
        int[] months = { 2, 7, 3, 4, 1, 6, 2, 8 };
        int[] dates = { 21, 27, 24, 14, 28, 16, 21, 15 };
        String[] occasions = { "Birthday", "birthday", "Mundan", "Anniversary", "Date", "birthday", "Mundan", "Freedom" };
        for (int i = 1; i <= names.length; i++) {
            PeopleEntity entry = new PeopleEntity();
            entry.setId(i);
            entry.setName(names[i - 1]);
            entry.setEmail(names[i - 1] + "@google.com");
            entry.setOccasion(occasions[i-1]);
            entry.setDate(LocalDate.of(years[i - 1], months[i - 1], dates[i - 1]));
            entry.setSubscription(true);

            underTest.save(entry);
        }
    }

    @Test
    void checksIfItSaves() {
        //Saving
        PeopleEntity entry = new PeopleEntity();
        entry.setId(10);
        entry.setName("Utkarsh");
        entry.setEmail("Utkarsh@google.com");
        entry.setOccasion("Birthday");
        entry.setDate(LocalDate.of(1998, 2, 21));
        entry.setSubscription(true);

        PeopleEntity savedObj = underTest.save(entry);

        assertEquals(9, savedObj.getId());
        assertEquals(entry.getName(), savedObj.getName());
        assertEquals(entry.getEmail(), savedObj.getEmail());
        assertEquals(entry.getOccasion(), savedObj.getOccasion());
        assertEquals(entry.getDate(), savedObj.getDate());
        assertEquals(entry.isSubscription(), savedObj.isSubscription());

        //Trying to updat with primary key 
        entry = new PeopleEntity();
        entry.setId(1);
        entry.setName("Not Utkarsh");
        entry.setEmail("NotUtkarsh@google.com");
        entry.setOccasion("Not Birthday");
        entry.setDate(LocalDate.of(1999, 4, 14));
        entry.setSubscription(false);
        
        savedObj = underTest.save(entry);
        
        assertEquals(10, savedObj.getId());
        assertEquals(entry.getName(), savedObj.getName());
        assertEquals(entry.getEmail(), savedObj.getEmail());
        assertEquals(entry.getOccasion(), savedObj.getOccasion());
        assertEquals(entry.getDate(), savedObj.getDate());
        assertEquals(entry.isSubscription(), savedObj.isSubscription());

        //Saving Empty Object
        entry = new PeopleEntity();
        savedObj = underTest.save(entry);
        assertEquals(11, savedObj.getId());
    }

    @Test
    void findAllByDateTodayTest() {
        setUp();
        List<PeopleEntity> peoples = underTest.findAllByDateToday(21, 2);
        assertEquals(2, peoples.size());
        assertEquals(peoples.get(0).getEmail(), "Utkarsh@google.com");
        assertEquals(peoples.get(1).getEmail(), "utalmighty@google.com");

        peoples = underTest.findAllByDateToday(15, 6);
        assertEquals(0, peoples.size());
    }

    @Test
    void findByEmailAndOccasionTest() {
        setUp();
        Optional<PeopleEntity> result = underTest.findByEmailAndOccasion("Utkarsh@google.com", "Birthday");
        assertTrue(result.isPresent());
        assertEquals(result.get().getEmail(), "Utkarsh@google.com");
        assertEquals(result.get().getOccasion(), "Birthday");

        result = underTest.findByEmailAndOccasion("Utkarsh@google.com", "BirTHday");
        assertTrue(!result.isPresent());

        result = underTest.findByEmailAndOccasion("Utkarsh@google.com", "Birthday ");
        assertTrue(!result.isPresent());

        result = underTest.findByEmailAndOccasion("utkarsh@google.com", "BirTHday");
        assertTrue(!result.isPresent());

        result = underTest.findByEmailAndOccasion("Utkarsh@google.com ", "Birthday ");
        assertTrue(!result.isPresent());
    }

    @Test
    void unsubscribeTest() {
        setUp();
        //double check the subscription value
        int val = underTest.unsubscribe("utalmighty@google.com");
        assertEquals(1, val);

        // unsubscribing again
        val = underTest.unsubscribe("utalmighty@google.com");
        assertEquals(1, val);

        //unsubscribing email with multiple records
        val = underTest.unsubscribe("Utkarsh@google.com");
        assertEquals(2, val);

        //unsubscribing email with no records
        val = underTest.unsubscribe("Utkarsh@gmail.com");
        assertEquals(0, val);
    }
}

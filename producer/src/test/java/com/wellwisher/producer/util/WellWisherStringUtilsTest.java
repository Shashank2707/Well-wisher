package com.wellwisher.producer.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class WellWisherStringUtilsTest {
	
	@Test
	void toTitleCaseTest() {
		String expected = "Utkarsh";
		assertEquals(expected, WellWisherStringUtils.toTitleCase("Utkarsh"));
		assertEquals(expected, WellWisherStringUtils.toTitleCase("utkarsh"));
		assertEquals(expected, WellWisherStringUtils.toTitleCase(" utkarsh "));
		assertEquals(expected, WellWisherStringUtils.toTitleCase(" utkarsh       "));
		assertEquals(expected, WellWisherStringUtils.toTitleCase("    utkarsh			"));
		assertEquals(expected, WellWisherStringUtils.toTitleCase("uTKArsh"));
		assertEquals(expected, WellWisherStringUtils.toTitleCase("	Utkarsh"));
		expected = "Utkarsh Jaiswal";
		assertEquals(expected, WellWisherStringUtils.toTitleCase("Utkarsh Jaiswal"));
		assertEquals(expected, WellWisherStringUtils.toTitleCase("Utkarsh    jaiswal"));
		assertEquals(expected, WellWisherStringUtils.toTitleCase("utkarsh jaISWal "));
		assertEquals(expected, WellWisherStringUtils.toTitleCase(" uTkarsh Jaiswal"));
		assertEquals(expected, WellWisherStringUtils.toTitleCase("    utkarsh    jaiswal "));
		assertEquals(expected, WellWisherStringUtils.toTitleCase("utkarsh jaiswal "));
		assertEquals(expected, WellWisherStringUtils.toTitleCase("utkarsh jaiswal"));
		expected = "Shah Rukh Khan";
		assertEquals(expected, WellWisherStringUtils.toTitleCase("shah rukh khan"));
		assertEquals(expected, WellWisherStringUtils.toTitleCase("SHAH RUKH KHAN"));
		assertEquals(expected, WellWisherStringUtils.toTitleCase(" shah Rukh Khan"));
		expected = "U Jaiswal";
		assertEquals(expected, WellWisherStringUtils.toTitleCase("u jaiswal"));
		assertEquals(expected, WellWisherStringUtils.toTitleCase("u  Jaiswal"));


		
	}

}

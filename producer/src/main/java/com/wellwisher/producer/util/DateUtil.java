package com.wellwisher.producer.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;

public class DateUtil {
	
	public static Date getStartTime() {
		LocalDateTime nextDayMidNight = LocalDateTime.of(LocalDate.now().plusDays(1), LocalTime.MIDNIGHT);
		ZoneId zone = ZoneId.of("Asia/Kolkata");
		ZoneOffset zoneOffSet = zone.getRules().getOffset(nextDayMidNight);
		return Date.from(nextDayMidNight.toInstant(zoneOffSet));
	}

}

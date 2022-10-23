package utili;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class MillisToDate {
	public static String getZonedDateTime(long tMillis) {
		Instant instant=Instant.ofEpochMilli(tMillis);
		ZonedDateTime zDateTime=instant.atZone(ZoneId.of("Asia/Kolkata"));
		DateTimeFormatter dTimeFormatter=DateTimeFormatter.ofPattern("dd:MM:yyyy HH:ss");
		return zDateTime.format(dTimeFormatter);
	}
	public static long getCurrentTimeInMillis() {
		return System.currentTimeMillis();
	}
	public static long getLongMillis(int lengthOfDays) {
		ZonedDateTime zDateTime=ZonedDateTime.now();
		zDateTime=zDateTime.minusDays(lengthOfDays);
		return zDateTime.toInstant().toEpochMilli();
	}
}

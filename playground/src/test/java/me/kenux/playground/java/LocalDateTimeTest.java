package me.kenux.playground.java;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

public class LocalDateTimeTest {

    @Test
    void localDateTimeTest() {
        LocalDateTime time = LocalDateTime.now();
        final String s = time.toString();
        System.out.println("s = " + s);

        final ZoneOffset zoneOffset = ZoneOffset.of("-10:00");
        final ZoneId zoneId = ZoneId.of("US/Pacific");
        System.out.println("zoneId = " + zoneId);
        System.out.println("zoneOffset = " + zoneOffset);
        System.out.println("time.atOffset(zoneOffset).toLocalDateTime() = " + time.atOffset(zoneOffset).toString());
        System.out.println("time.atZone(zoneId) = " + time.atZone(zoneId));

        System.out.println("time.getMonth() = " + time.getMonth().toString());

        final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
        final String formattedDateTimeString = time.format(dateTimeFormatter);
        System.out.println("formattedDateTimeString = " + formattedDateTimeString);
    }


    @Test
    void test2() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        LocalDateTime messageDateTime = LocalDateTime.parse("2022-07-20 17:20:38.234", formatter);
        System.out.println("messageDateTime = " + messageDateTime);
    }

    @Test
    void test3() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        final OffsetDateTime dateTime = OffsetDateTime.parse("2022-08-17T22:00:00+09:00");
        System.out.println("dateTime = " + dateTime);
        System.out.println("dateTime.getOffset() = " + dateTime.getOffset());
    }
}

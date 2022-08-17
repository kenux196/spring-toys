package me.kenux.playground.datetime;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.data.Offset;
import org.junit.jupiter.api.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.zone.ZoneOffsetTransition;
import java.util.Set;
import java.util.TimeZone;

@Slf4j
class DateTimeTest {

/**
 * |----------------------  ZonedDateTime -------------------------------|
 * |---------------- OffsetDateTime ------------------|
 * |----------- LocalDateTime -----|
 * |---LocalDate---|---LocalTime---|--- ZoneOffset ---|--- ZoneRegion ---|
 *   2022-08-10         12:00:00         +09:00            Asia/Seoul
 */

    @Test
    void localDateTimeTest() {
        /**
         *  LocalDateTime: 시간대(ZoneOffset, ZoneRegion)에 대한 정보가 없는 API
         *  한국: 2022-08-10T09:00:00 --> 미국: 2022-08-10T09:00:00
         */
        setTimeZoneUtc();
        final LocalDateTime localDateTime = LocalDateTime.of(2022, 8, 10, 19, 20, 20);
        System.out.println("localDateTime = " + localDateTime);

        setTimeZoneAsiaSeoul();
        System.out.println("localDateTime = " + localDateTime);
    }

    @Test
    void zoneOffsetTest() {
        /**
         * UTC 기준으로 시간(Time Offset)을 나타냄
         * 한국: KST --> UTC +09:00
         */
        System.out.println("ZoneOffset.of(\"+9\") = " + ZoneOffset.of("+9"));
        System.out.println("ZoneId.of(\"+9\") = " + ZoneId.of("+9"));
        System.out.println("ZoneId.of(\"-9\") = " + ZoneId.of("-9"));
    }

    @Test
    void zoneRegionTest() {
        /**
         * Time Zone을 나타낸 것
         * KST: 타임존 이름
         * ZoneRegion: Asia/Seoul
         */
        System.out.println("ZoneId.of(\"Asia/Seoul\") = " + ZoneId.of("Asia/Seoul"));
        System.out.println("ZoneId.of(\"UTC+9\") = " + ZoneId.of("UTC+9"));
    }

    /**
     * ZoneRules
     * DST(Daylight saving time, 썸머타임) 포함 여부에 따라.
     * ZoneOffset: Time Transition Rule 미포함
     * ZoneRegion: Time Transition Rule 포함 or 미포함
     */
    @Test
    void ZoneRuleTest() {
        System.out.println("ZoneOffset이기 때문에 Time Transition Rule 없다.");
        ZoneOffset.of("+1").getRules().getTransitionRules().forEach(System.out::println);

        System.out.println("ZoneRegion 이지만, Time Transition Rule 없어서 아무것도 안나온다.");
        ZoneId.of("Africa/Brazzaville").getRules().getTransitionRules()
            .forEach(System.out::println);

        System.out.println("ZoneRegion 이고, Time Transition Rule 있다");
        ZoneId.of("CET").getRules().getTransitionRules().forEach(System.out::println);
    }

    /**
     * OffsetDateTime : LocalDateTime + ZoneOffset
     */
    @Test
    void OffsetDateTimeTest() {
        LocalDateTime localDateTime = LocalDateTime.of(2022, 8, 10, 19, 20, 20);
        final OffsetDateTime offsetDateTime1 = OffsetDateTime.of(localDateTime, ZoneOffset.of("+2"));
        System.out.println("offsetDateTime1 = " + offsetDateTime1);
    }

    @Test
    void ConvertLocalTimeTest() {
        setTimeZoneUtc();
        final OffsetDateTime utc = OffsetDateTime.of(LocalDateTime.now(), ZoneOffset.UTC);
        System.out.println("utc = " + utc);

        final OffsetDateTime kst = utc.withOffsetSameInstant(ZoneOffset.of("+9"));
        System.out.println("kst = " + kst);

        final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mm:ss");
        System.out.println("kst = " + dateTimeFormatter.format(kst));
    }

    @Test
    void OffsetTest() {
        setTimeZoneUtc();
        final Set<String> availableZoneIds = ZoneOffset.getAvailableZoneIds();
        for (String availableZoneId : availableZoneIds) {
            System.out.println("availableZoneId = " + availableZoneId + " Offset");
            System.out.println("TimeZone.getTimeZone(availableZoneId) = " +
                TimeZone.getTimeZone(availableZoneId));
        }
    }


    private void setTimeZoneUtc() {
        TimeZone.setDefault(TimeZone.getTimeZone(ZoneId.of("UTC")));
    }

    private void setTimeZoneAsiaSeoul() {
        TimeZone.setDefault(TimeZone.getTimeZone(ZoneId.of("Asia/Seoul")));
    }

}

package datetime;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Date;

public class LocalDateTimeTest {

    @Test
    void localAndOffset() {
        final LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println("localDateTime = " + localDateTime);

        final OffsetDateTime offsetDateTime = OffsetDateTime.now();
        System.out.println("offsetDateTime = " + offsetDateTime);

        final Date date = new Date();
        System.out.println("date = " + date);
    }
}
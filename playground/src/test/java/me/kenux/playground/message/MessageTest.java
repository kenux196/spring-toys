package me.kenux.playground.message;

import lombok.extern.slf4j.Slf4j;
import me.kenux.playground.jpa.config.MessageConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.MessageSourceAccessor;

import java.util.Locale;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest
class MessageTest {
    @Autowired
    private MessageSource messageSource;


    @Test
    void executeMessage() {
        final String enterEn = messageSource.getMessage("common.enter", null, Locale.ENGLISH);
        final String enterKo = messageSource.getMessage("common.enter", null, Locale.KOREAN);
        assertThat(enterEn).isEqualTo("Enter");
        assertThat(enterKo).isEqualTo("확인");
        log.info("en_US : {}", enterEn);
        log.info("ko_KR : {}", enterKo);
    }

}

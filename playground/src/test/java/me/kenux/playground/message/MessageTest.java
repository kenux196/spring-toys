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
        final String enterEn = messageSource.getMessage("hello", null, Locale.ENGLISH);
        final String enterKo = messageSource.getMessage("hello", null, Locale.KOREAN);
        final String enterKoKR = messageSource.getMessage("hello", null, Locale.KOREA);
        assertThat(enterEn).isEqualTo("hello");
        assertThat(enterKo).isEqualTo("안녕");
        assertThat(enterKoKR).isEqualTo("안녕!");
        log.info("en : {}", enterEn);
        log.info("ko : {}", enterKo);
        log.info("ko_KR : {}", enterKoKR);
    }

}

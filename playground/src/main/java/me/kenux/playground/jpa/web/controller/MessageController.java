package me.kenux.playground.jpa.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@Slf4j
@RestController
@RequestMapping("/messages")
@RequiredArgsConstructor
public class MessageController {

    private final MessageSource messageSource;

    @GetMapping
    public ResponseEntity<String> getMessage(@RequestParam(value = "lang") String language,
                                             @RequestParam(value = "country", required = false) String country) {

        Locale locale = new Locale(language);
        if (country != null) {
            locale = new Locale(language, country);
        }
        log.info("currentLocale={}", locale);

        final String hello = messageSource.getMessage("hello", null, locale);
        log.info("hello={}", hello);
        return ResponseEntity.ok(hello);
    }

}

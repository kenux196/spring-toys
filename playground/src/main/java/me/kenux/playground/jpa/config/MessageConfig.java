package me.kenux.playground.jpa.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

//@Configuration
public class MessageConfig {

//    @Bean
//    public MessageSource messageSource() {
//        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
////        messageSource.setBasename("classpath:/messages/");
//        messageSource.setDefaultEncoding("UTF-8");
//        messageSource.setFallbackToSystemLocale(false); // Locale에 해당하는 file이 없는 경우 system locale 사용
//        return messageSource;
//    }
//
//    @Bean
//    public MessageSourceAccessor messageSourceAccessor() {
//        return new MessageSourceAccessor(messageSource());
//    }
}

package me.kenux.typeconverter;

import me.kenux.typeconverter.converter.IntegerToStringConverter;
import me.kenux.typeconverter.converter.IpPortToStringConverter;
import me.kenux.typeconverter.converter.StringToIntegerConverter;
import me.kenux.typeconverter.converter.StringToIpPortConverter;
import me.kenux.typeconverter.formatter.MyNumberFormatter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        // 주석처리 우선순위
//        registry.addConverter(new StringToIntegerConverter());
//        registry.addConverter(new IntegerToStringConverter());
        registry.addConverter(new StringToIpPortConverter());
        registry.addConverter(new IpPortToStringConverter());

        // 추가
        registry.addFormatter(new MyNumberFormatter());
    }
}

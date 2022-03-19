package me.kenux.typeconverter;

import me.kenux.typeconverter.converter.IntegerToStringConverter;
import me.kenux.typeconverter.converter.IpPortToStringConverter;
import me.kenux.typeconverter.converter.StringToIntegerConverter;
import me.kenux.typeconverter.converter.StringToIpPortConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToIntegerConverter());
        registry.addConverter(new IntegerToStringConverter());
        registry.addConverter(new StringToIpPortConverter());
        registry.addConverter(new IpPortToStringConverter());
    }
}

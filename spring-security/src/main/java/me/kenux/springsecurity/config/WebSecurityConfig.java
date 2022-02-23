package me.kenux.springsecurity.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.authentication.DefaultAuthenticationEventPublisher;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Slf4j
@EnableWebSecurity
public class WebSecurityConfig
//        extends WebSecurityConfigurerAdapter
{

    @Bean
    public PasswordEncoder passwordEncoder() {
        log.debug("Create PasswordEncoder bean");
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        final String password = passwordEncoder().encode("1234");
        log.debug("===== password = {}", password);

        UserDetails user1 = User.withUsername("user")
                .password(password)
                .roles("USER")
                .build();
        UserDetails user2 = User.withUsername("kenux")
                .password(password)
                .roles("USER")
                .build();
        UserDetails admin = User.withUsername("admin")
                .password("1234")
                .roles("USER", "ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user1, user2, admin);
    }

    @Bean
    public AuthenticationEventPublisher authenticationEventPublisher(
            ApplicationEventPublisher applicationEventPublisher) {
        return new DefaultAuthenticationEventPublisher(applicationEventPublisher);
    }

    @Configuration
//    @Order(1)
    public static class ApiWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .antMatcher("/api/**")
                    .authorizeRequests(authorize -> authorize.anyRequest().hasRole("ADMIN"))
                    .httpBasic()
            ;
        }
    }

    @Configuration
    @Order(1)
    public static class FormLoginWebSecurityConfigureAdapter extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .authorizeRequests(authorize -> authorize
                        .antMatchers("/resources/**", "/login").permitAll()
                        .antMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated())
                .formLogin(login -> login
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/hello", true))
            ;
        }
    }


//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .authorizeRequests(authorize -> authorize
//                        .antMatchers("/resources/**", "/login").permitAll()
//                        .antMatchers("/admin/**").hasRole("ADMIN")
//                        .anyRequest().authenticated())
//                .formLogin(login -> login
//                        .loginPage("/login")
//                        .loginProcessingUrl("/login")
//                        .defaultSuccessUrl("/hello", true))
//        ;
//
//    }

}
package me.kenux.springsecurity.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.authentication.DefaultAuthenticationEventPublisher;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
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
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        log.debug("Create PasswordEncoder bean");
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        final String password = passwordEncoder().encode("1234");
        log.debug("===== password = {}", password);

        UserDetails user1 = User.withUsername("manager")
                .password(password)
                .roles("ADMIN")
                .build();
        UserDetails user2 = User.withUsername("kenux")
                .password(password)
                .roles("USER")
                .build();
        UserDetails admin = User.withUsername("admin")
                .password(password)
                .roles("USER", "ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user1, user2, admin);
    }

    @Bean
    public AuthenticationEventPublisher authenticationEventPublisher(
            ApplicationEventPublisher applicationEventPublisher) {
        return new DefaultAuthenticationEventPublisher(applicationEventPublisher);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // js, css, image 파일 등 보안 필터 적용이 필요없는 리소스 설정
        web.ignoring()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 인가 정책
        http
//                    .authorizeRequests()
//                    .anyRequest().permitAll()
                .authorizeRequests(authorize -> authorize
                        .antMatchers("/resources/**", "/login", "/logout").permitAll()
                        .antMatchers("/admin/**").hasRole("ADMIN")
                        .antMatchers("/books/add").hasRole("ADMIN")
                        .antMatchers("/books/edit").hasRole("ADMIN")
                        .antMatchers("/books/delete").hasRole("ADMIN")
                        .antMatchers("/main").authenticated()
                        .anyRequest().permitAll())
        ;

        // 인증 정책
        http
                .csrf().disable()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/main", true)
        ;
    }
}
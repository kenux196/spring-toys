package me.kenux.springsecurity.basic;

import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.*;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

public class PasswordEncoderTest {

    // 적응형 단방향 함수 (Adaptive one-way function) 이용한 암호화
    private final PasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    //    private final PasswordEncoder argon2PasswordEncoder = new Argon2PasswordEncoder();
    private final PasswordEncoder pbkdf2PasswordEncoder = new Pbkdf2PasswordEncoder();
//    private final PasswordEncoder sCryptPasswordEncoder = new SCryptPasswordEncoder();

    // Spring Security 에서 제공하는 기능.
    private final PasswordEncoder delegatingPasswordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    @Test
    void encodeTest() {
        String origin = "password1004!";

        final String encode = bCryptPasswordEncoder.encode(origin);
        System.out.println("bCryptPasswordEncoder result = " + encode);

        final String encode2 = pbkdf2PasswordEncoder.encode(origin);
        System.out.println("pbkdf2PasswordEncoder result = " + encode2);

        final String encode1 = delegatingPasswordEncoder.encode(origin);
        System.out.println("delegatingPasswordEncoder result = " + encode1);
    }

    @Test
    void customDelegatingPasswordEncoderTest() {
        String idForEncode = "bcrypt";
        Map encoder = new HashMap<>();
        encoder.put(idForEncode, new BCryptPasswordEncoder());
        encoder.put("noop", NoOpPasswordEncoder.getInstance());
        encoder.put("pbkdf2", new Pbkdf2PasswordEncoder());
        encoder.put("scrpty", new SCryptPasswordEncoder());
        encoder.put("sha256", new StandardPasswordEncoder());

        final PasswordEncoder passwordEncoder = new DelegatingPasswordEncoder(idForEncode, encoder);
        final String encode = passwordEncoder.encode("password1004!");
        // {bcrypt}$2a$10$O3fGI1o8ItCbPmnXT4pSNubwEy4hhSabo2wgyPnVjq9JYIB8sd96S
        System.out.println("encode = " + encode);

        final PasswordEncoder passwordEncoder1 = new DelegatingPasswordEncoder("sha256", encoder);
        final String encode1 = passwordEncoder1.encode("password1004!");
        // {sha256}79d3ee71899f3d79265fb1f9d18d63d19c4cf03d4ac0b2a842085d268718b06321bbc0b3c3d823b5
        System.out.println("encode1 = " + encode1);
    }

    @Test
    void PasswordMatchingTest() {
        String encode = delegatingPasswordEncoder.encode("password1004!");
        // {bcrypt}$2a$10$O3fGI1o8ItCbPmnXT4pSNubwEy4hhSabo2wgyPnVjq9JYIB8sd96S

        boolean matches = delegatingPasswordEncoder.matches("password1004!", encode);
        assertThat(matches).isTrue();

        matches = delegatingPasswordEncoder.matches("password", encode);
        assertThat(matches).isFalse();
    }

    @Test
    void withDefaultPasswordEncoderTest() {
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("password")
                .roles("user")
                .build();

        // {bcrypt}$2a$10$vEh2WQIXvDDZGwdxkaScg.NOsbu0.RK5W0KIUa/JB4PSOMJO1dp02
        System.out.println("user.getPassword() = " + user.getPassword());
    }

    @Test
    void bCryptPasswordEncoderTest() {
        // Create an encoder with strength 16
        final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);
        final String result = encoder.encode("password1004!");
        assertThat(encoder.matches("password1004!", result)).isTrue();
    }
}
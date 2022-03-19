package me.kenux.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

class CheckedExceptionTests {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void throw_던지기() throws JsonProcessingException {
        objectMapper = new ObjectMapper();
        Member member = new Member("kenux");
        String valueAsString = objectMapper.writeValueAsString(member);
    }

    @Test
    void try_catch_감싸기() {
        Member member = new Member("kenux");
        String valueAsString;

        try {
            valueAsString = objectMapper.writeValueAsString(member);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public static class Member {
        private String name;

        public Member(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}

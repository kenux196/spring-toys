package me.kenux.jpalearn.anything;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class InheritanceTest {

    @Test
    void test1() {
        final Child child = new Child("김", "개똥");
        child.getFamilyName();
    }


    public class Parent {

        private final String familyName;

        public Parent(String familyName) {
            this.familyName = familyName;
        }

        public String getFamilyName() {
            return familyName;
        }
    }

    public class Child extends Parent {
        private final String name;

        public Child(String familyName, String name) {
            super(familyName);
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
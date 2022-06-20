package me.kenux.playground.jpa.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "student")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Student {

    @Id
    private String id;

    private String name;

    private String grade;

    @Column(name = "class")
    private String studyClass;

    public Student(String id, String name) {
        this.id = id;
        this.name = name;
    }
}

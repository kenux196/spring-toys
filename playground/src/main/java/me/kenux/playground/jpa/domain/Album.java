package me.kenux.playground.jpa.domain;

import javax.persistence.Entity;

@Entity
public class Album extends Item {

    private String artist;
}
package me.kenux.jpalearn.domain.member.domain;

public class Movie extends Item {

    private String director;
    private String actor;

    public Movie(String id, String name, int price, String director, String actor) {
        super(id, name, price);
        this.director = director;
        this.actor = actor;
    }
}
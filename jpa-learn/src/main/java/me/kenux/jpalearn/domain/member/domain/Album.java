package me.kenux.jpalearn.domain.member.domain;

public class Album extends Item {

    private String artist;

    public Album(String id, String name, int price, String artist) {
        super(id, name, price);
        this.artist = artist;
    }

    public String getArtist() {
        return artist;
    }
}
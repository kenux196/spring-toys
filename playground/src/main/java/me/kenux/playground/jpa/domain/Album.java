package me.kenux.playground.jpa.domain;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Album extends Item {

    private String artist;

    public Album(String name, int price, int stockQuantity, String artist) {
        super(name, price, stockQuantity);
        this.artist = artist;
    }
}
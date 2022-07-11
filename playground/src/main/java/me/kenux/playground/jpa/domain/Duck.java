package me.kenux.playground.jpa.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import me.kenux.playground.jpa.domain.listener.DuckListener;

import javax.persistence.*;

@Slf4j
@NoArgsConstructor
@Getter @Setter
@Entity
@EntityListeners(DuckListener.class)
public class Duck {

    @Id
    @GeneratedValue
    public Long id;

    private String name;

    @PrePersist
    public void prePersist() {
        log.info("Duck.prePersist id={}", id);
    }

    @PostPersist
    public void postPersist() {
        log.info("Duck.postPersist id={}", id);
    }

    @PostLoad
    public void postLoad() {
        log.info("Duck.postLoad");
    }

    @PreRemove
    public void preRemove() {
        log.info("Duck.preRemove");
    }

    @PostRemove
    public void postRemove() {
        log.info("Duck.postRemove");
    }

    public Duck(String name) {
        this.name = name;
    }
}

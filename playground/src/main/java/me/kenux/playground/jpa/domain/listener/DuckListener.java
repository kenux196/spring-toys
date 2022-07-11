package me.kenux.playground.jpa.domain.listener;

import lombok.extern.slf4j.Slf4j;

import javax.persistence.PostPersist;
import javax.persistence.PrePersist;

@Slf4j
public class DuckListener {

    @PrePersist
    // 특정 타입이 확실하면 특정 타입을 받을 수 있다.
    private void prePersist(Object obj) {
        log.info("DuckListener.prePersist obj = ", obj);
    }

    @PostPersist
    private void postPersist(Object obj) {
        log.info("DuckListener.postPersist obj = ", obj);
    }
}

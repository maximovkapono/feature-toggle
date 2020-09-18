package com.swiss.feature.toggles.service.logic;

import java.time.LocalDateTime;

public class Expired {

    private final LocalDateTime expired;
    private final LocalDateTime dateNow;

    public Expired(LocalDateTime expired) {
        this.expired = expired;
        this.dateNow = LocalDateTime.now();
    }

    public boolean get() {
        return expired != null && dateNow.isAfter(expired);
    }
}

package com.swiss.feature.toggles.service.logic;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Active {

    private final boolean expired;
    private final boolean inverted;
    private final boolean customerInList;

    public boolean get() {
        return expired || (!inverted && customerInList);
    }
}

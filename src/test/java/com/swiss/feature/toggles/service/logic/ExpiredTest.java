package com.swiss.feature.toggles.service.logic;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ExpiredTest {

    @Test
    @DisplayName("Null value test")
    void nullTest() {
        boolean expired = new Expired(null).get();
        assertFalse(expired);
    }

    @Test
    @DisplayName("Future date test")
    void falseTest() {
        boolean expired = new Expired(LocalDateTime.now().plusYears(1)).get();
        assertFalse(expired);
    }

    @Test
    @DisplayName("Past date test")
    void trueTest() {
        boolean expired = new Expired(LocalDateTime.now().minusYears(1)).get();
        assertTrue(expired);
    }
}
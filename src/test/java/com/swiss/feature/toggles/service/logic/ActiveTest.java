package com.swiss.feature.toggles.service.logic;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ActiveTest {

    @DisplayName("Feature active test")
    @ParameterizedTest(name = "Feature with inverted:{0}, expired:{1}, customerInList:{2} should be active:{3}")
    @CsvSource({
            "false, false, false, false",
            "false, false, true, true",
            "false, true, false, true",
            "false, true, true, true",
            "true, false, false, false",
            "true, false, true, false",
            "true, true, false, true",
            "true, true, true, true"
    })
    void unitTest(boolean inverted, boolean expired, boolean customerInList, boolean expected) {
        boolean active = new Active(expired, inverted, customerInList).get();
        assertEquals(expected, active);
    }
}
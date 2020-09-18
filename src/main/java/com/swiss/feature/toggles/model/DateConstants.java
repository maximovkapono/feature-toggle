package com.swiss.feature.toggles.model;

import java.time.format.DateTimeFormatter;

public interface DateConstants {
    DateTimeFormatter DATE_TIME_PATTERN = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
}

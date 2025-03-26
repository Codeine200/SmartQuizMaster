package com.codeine.smartquizmaster.model;

import java.util.Arrays;
import java.util.stream.Stream;

public enum QuestionType {
    TEXT,
    SINGLE,
    MULTIPLE;

    public boolean in(QuestionType... type) {
        return Stream.of(type)
                .anyMatch(t -> this == t);
    }
}

package io.rocketbase.mail.styling;

import lombok.Getter;

public enum Alignment {
    LEFT("left"),
    CENTER("center"),
    RIGHT("right");

    @Getter
    private String value;

    Alignment(String value) {
        this.value = value;
    }
}

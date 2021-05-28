package io.rocketbase.mail.styling;

import lombok.Getter;

public enum Side {
    LEFT("left"),
    RIGHT("right");

    @Getter
    private String value;

    Side(String value) {
        this.value = value;
    }
}

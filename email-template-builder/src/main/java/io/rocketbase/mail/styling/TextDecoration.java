package io.rocketbase.mail.styling;

import lombok.Getter;

public enum TextDecoration {

    UNDERLINE("underline"),
    OVERLINE("overline"),
    LINE_THROUGH("line-through"),
    BLINK("blink"),
    NONE("none");

    @Getter
    private String value;

    TextDecoration(String value) {
        this.value = value;
    }
}

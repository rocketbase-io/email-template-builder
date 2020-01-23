package io.rocketbase.commons.email.template.styling;

import lombok.Getter;

public enum TextDecoration {

    UNDERLINE("underline"),
    OVERLINE("overline"),
    LINE_THROUGH("line-through"),
    BLINK("blink");

    @Getter
    private String value;

    TextDecoration(String value) {
        this.value = value;
    }
}

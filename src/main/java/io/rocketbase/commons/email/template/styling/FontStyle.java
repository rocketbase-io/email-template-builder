package io.rocketbase.commons.email.template.styling;

import lombok.Getter;

public enum FontStyle {

    NORMAL("normal"),
    ITALIC("italic"),
    OBLIQUE("oblique");

    @Getter
    private String value;

    FontStyle(String value) {
        this.value = value;
    }
}

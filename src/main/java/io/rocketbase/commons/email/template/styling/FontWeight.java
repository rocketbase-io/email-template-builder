package io.rocketbase.commons.email.template.styling;

import lombok.Getter;

public enum FontWeight {

    NORMAL("normal"),
    BOLD("bold"),
    LIGHTER("lighter");

    @Getter
    private String value;

    FontWeight(String value) {
        this.value = value;
    }
}

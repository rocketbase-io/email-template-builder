package io.rocketbase.mail.styling;

import lombok.Getter;

public enum ObjectFit {
    CONTAIN("contain"),
    COVER("cover"),
    FILL("fill"),
    INHERENT("inherent"),
    INITIAL("initial"),
    NONE("none"),
    REVERT("revert"),
    REVERT_LAYER("revert-layer"),
    SCALE_DOWN("scale-down"),
    UNSET("unset");

    @Getter
    private String value;

    ObjectFit(String value) {
        this.value = value;
    }
}

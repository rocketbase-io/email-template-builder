package io.rocketbase.mail.styling;

import lombok.Getter;

public enum VerticalAlignment {

   BASELINE("baseline"),
   SUB("sub"),
   SUPER("super"),
   TEXT_TOP("text-top"),
   TEXT_BOTTOM("text-bottom"),
   MIDDLE("middle"),
   TOP("top"),
   BOTTOM("bottom");


    @Getter
    private String value;

    VerticalAlignment(String value) {
        this.value = value;
    }
}

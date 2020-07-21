package io.rocketbase.mail.config.base;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class TbFont {
    private final String size;
    private final String color;

    public TbFont(TbFont other) {
        this.size = other.size;
        this.color = other.color;
    }

}

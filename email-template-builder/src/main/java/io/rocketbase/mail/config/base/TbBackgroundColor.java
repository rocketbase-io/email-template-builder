package io.rocketbase.mail.config.base;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class TbBackgroundColor {
    private final String background;
    private final String color;

    public TbBackgroundColor(TbBackgroundColor other) {
        this.background = other.background;
        this.color = other.color;
    }
}

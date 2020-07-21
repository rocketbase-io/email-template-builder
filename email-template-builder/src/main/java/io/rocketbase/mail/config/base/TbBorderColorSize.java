package io.rocketbase.mail.config.base;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class TbBorderColorSize {
    private final String color;
    private final String size;

    public TbBorderColorSize(TbBorderColorSize other) {
        this.color = other.color;
        this.size = other.size;
    }
}

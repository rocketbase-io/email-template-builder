package io.rocketbase.mail.config.base;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class TbBorderDetailed {
    private final String top;
    private final String right;
    private final String bottom;
    private final String left;

    public TbBorderDetailed(TbBorderDetailed other) {
        this.top = other.top;
        this.right = other.right;
        this.bottom = other.bottom;
        this.left = other.left;
    }
}

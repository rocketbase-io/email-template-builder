package io.rocketbase.mail.config.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class TbContentConfig {

    public static final TbContentConfig DEFAULT = new TbContentConfig("#FFFFFF",
            570,
            "35px");

    private final String background;
    private final Integer width;
    private final String padding;

    public TbContentConfig(TbContentConfig other) {
        this.background = other.background;
        this.width = other.width;
        this.padding = other.padding;
    }
}

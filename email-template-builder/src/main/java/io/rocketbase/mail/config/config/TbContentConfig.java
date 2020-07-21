package io.rocketbase.mail.config.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class TbContentConfig {

    public static final TbContentConfig DEFAULT = new TbContentConfig("#FFFFFF",
            570,
            false,
            "35px");

    private final String background;
    /**
     * width of inner-content
     */
    private final Integer width;
    /**
     * table around content - full width, then white till edges otherwise gray
     */
    private final boolean full;
    private final String padding;

    public TbContentConfig(TbContentConfig other) {
        this.background = other.background;
        this.width = other.width;
        this.full = other.full;
        this.padding = other.padding;
    }
}

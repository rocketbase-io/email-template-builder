package io.rocketbase.mail.config.config;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TbContentConfig {

    static final TbContentConfig DEFAULT = new TbContentConfig("#FFFFFF",
            570,
            false,
            "35px");

    public static final TbContentConfig newInstance() {
        return new TbContentConfig(DEFAULT);
    }

    private String background;
    /**
     * width of inner-content
     */
    private Integer width;
    /**
     * table around content - full width, then white till edges otherwise gray
     */
    private boolean full;
    private String padding;

    public TbContentConfig(TbContentConfig other) {
        this.background = other.background;
        this.width = other.width;
        this.full = other.full;
        this.padding = other.padding;
    }
}

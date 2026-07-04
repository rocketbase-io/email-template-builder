package io.rocketbase.mail.config.config;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TbContentConfig {

    static final TbContentConfig DEFAULT = new TbContentConfig("#FFFFFF",
            570,
            false,
            "35px",
            "center");

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
    /**
     * horizontal placement of the content-block within the viewport: center (default) or left
     */
    private String align;

    public TbContentConfig(TbContentConfig other) {
        this.background = other.background;
        this.width = other.width;
        this.full = other.full;
        this.padding = other.padding;
        this.align = other.align;
    }
}

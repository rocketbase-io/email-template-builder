package io.rocketbase.mail.config.config;

import io.rocketbase.mail.config.base.TbFont;
import lombok.Getter;

@Getter
public class TbTextConfig extends TbFont {

    public static final TbTextConfig DEFAULT = new TbTextConfig(new TbFont("16px", "#51545E"),
            ".4em 0 1.1875em",
            "1.625",
            "#3869D4");

    private final String margin;
    private final String lineHeight;
    private final String linkColor;

    public TbTextConfig(TbFont font, String margin, String lineHeight, String linkColor) {
        super(font);
        this.margin = margin;
        this.lineHeight = lineHeight;
        this.linkColor = linkColor;
    }

    public TbTextConfig(TbTextConfig other) {
        super(other);
        this.margin = other.margin;
        this.lineHeight = other.lineHeight;
        this.linkColor = other.linkColor;
    }
}

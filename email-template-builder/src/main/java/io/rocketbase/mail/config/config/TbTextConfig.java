package io.rocketbase.mail.config.config;

import io.rocketbase.mail.config.base.TbFont;
import lombok.Data;

@Data
public class TbTextConfig extends TbFont {

    static final TbTextConfig DEFAULT = new TbTextConfig(new TbFont("16px", "#51545E"),
            ".4em 0 1.1875em",
            "1.625",
            "#3869D4",
            "#FFFFFF");

    static final TbTextConfig SMALL = new TbTextConfig(new TbFont("16px", "#51545E"),
            ".4em 0 .4em",
            "1.325",
            "#3869D4",
            "#FFFFFF");

    public static final TbTextConfig newInstance() {
        return new TbTextConfig(DEFAULT);
    }

    public static final TbTextConfig newInstanceSmall() {
        return new TbTextConfig(SMALL);
    }

    private String margin;
    private String lineHeight;
    private String linkColor;
    private String linkColorDark;

    public TbTextConfig(TbFont font, String margin, String lineHeight, String linkColor, String linkColorDark) {
        super(font);
        this.margin = margin;
        this.lineHeight = lineHeight;
        this.linkColor = linkColor;
        this.linkColorDark = linkColorDark;
    }

    public TbTextConfig(TbTextConfig other) {
        super(other);
        this.margin = other.margin;
        this.lineHeight = other.lineHeight;
        this.linkColor = other.linkColor;
        this.linkColorDark = other.linkColorDark;
    }
}

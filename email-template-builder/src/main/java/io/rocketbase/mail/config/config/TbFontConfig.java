package io.rocketbase.mail.config.config;

import io.rocketbase.mail.config.base.TbFont;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TbFontConfig {

    static TbFontConfig DEFAULT = new TbFontConfig("@import url('https://fonts.googleapis.com/css?family=Nunito+Sans:400,700&display=swap');",
            "'Nunito Sans', Helvetica, Arial, sans-serif",
            new TbFont("22px", "#333333"),
            new TbFont("16px", "#333333"),
            new TbFont("14px", "#333333"),
            new TbFont("16px", "#333333"),
            new TbFont("13px", "#6B6E76"));

    public static final TbFontConfig newInstance() {
        return new TbFontConfig(DEFAULT);
    }

    private String cssImport;
    private String family;
    private TbFont h1;
    private TbFont h2;
    private TbFont h3;
    private TbFont table;
    private TbFont sub;

    public TbFontConfig(TbFontConfig other) {
        this.cssImport = other.cssImport;
        this.family = other.family;
        this.h1 = new TbFont(other.h1);
        this.h2 = new TbFont(other.h2);
        this.h3 = new TbFont(other.h3);
        this.table = new TbFont(other.table);
        this.sub = new TbFont(other.sub);
    }
}

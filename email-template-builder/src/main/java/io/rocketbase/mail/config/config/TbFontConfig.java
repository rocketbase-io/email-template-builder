package io.rocketbase.mail.config.config;

import io.rocketbase.mail.config.base.TbFont;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class TbFontConfig {

    public static TbFontConfig DEFAULT = new TbFontConfig("@import url('https://fonts.googleapis.com/css?family=Nunito+Sans:400,700&display=swap');",
            "'Nunito Sans', Helvetica, Arial, sans-serif",
            new TbFont("22px", "#333333"),
            new TbFont("16px", "#333333"),
            new TbFont("14px", "#333333"),
            new TbFont("16px", "#333333"),
            new TbFont("13px", "#6B6E76"));

    private final String cssImport;
    private final String family;
    private final TbFont h1;
    private final TbFont h2;
    private final TbFont h3;
    private final TbFont table;
    private final TbFont sub;

    public TbFontConfig(TbFontConfig other) {
        this.cssImport = other.cssImport;
        this.family = other.family;
        this.h1 = other.h1;
        this.h2 = other.h2;
        this.h3 = other.h3;
        this.table = other.table;
        this.sub = other.sub;
    }
}

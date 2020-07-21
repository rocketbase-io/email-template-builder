package io.rocketbase.mail.config;

import io.rocketbase.mail.config.config.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class TbConfiguration {

    public static final TbConfiguration DEFAULT = new TbConfiguration(TbFontConfig.DEFAULT,
            TbTextConfig.DEFAULT,
            TbButtonConfig.DEFAULT,
            TbAttributeConfig.DEFAULT,
            TbBoxConfig.DEFAULT,
            TbTableConfig.DEFAULT,
            TbBodyConfig.DEFAULT,
            TbHeaderConfig.DEFAULT,
            TbContentConfig.DEFAULT,
            TbFooterConfig.DEFAULT);

    private final TbFontConfig font;
    private final TbTextConfig text;
    private final TbButtonConfig button;
    private final TbAttributeConfig attribute;
    private final TbBoxConfig box;
    private final TbTableConfig table;
    private final TbBodyConfig body;
    private final TbHeaderConfig header;
    private final TbContentConfig content;
    private final TbFooterConfig footer;

    public TbConfiguration(TbConfiguration other) {
        this.font = other.font;
        this.text = other.text;
        this.button = other.button;
        this.attribute = other.attribute;
        this.box = other.box;
        this.table = other.table;
        this.body = other.body;
        this.header = other.header;
        this.content = other.content;
        this.footer = other.footer;
    }
}

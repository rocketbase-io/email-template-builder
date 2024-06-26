package io.rocketbase.mail.config;

import io.rocketbase.mail.config.config.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TbConfiguration {

    static final TbConfiguration DEFAULT = new TbConfiguration(true, TbFontConfig.newInstance(),
            TbTextConfig.newInstance(),
            TbTextConfig.newInstanceSmall(),
            TbSideImageLineConfig.newInstance(),
            TbButtonConfig.newInstance(),
            TbAttributeConfig.newInstance(),
            TbBoxConfig.newInstance(),
            TbTableConfig.newInstance(),
            TbBodyConfig.newInstance(),
            TbHeaderConfig.newInstance(),
            TbContentConfig.newInstance(),
            TbFooterConfig.newInstance());

    public static final TbConfiguration newInstance() {
        return new TbConfiguration(DEFAULT);
    }

    private boolean darkModeEnabled = true;
    private TbFontConfig font;
    private TbTextConfig text;
    private TbTextConfig imageText;
    private TbSideImageLineConfig sideImageLine;
    private TbButtonConfig button;
    private TbAttributeConfig attribute;
    private TbBoxConfig box;
    private TbTableConfig table;
    private TbBodyConfig body;
    private TbHeaderConfig header;
    private TbContentConfig content;
    private TbFooterConfig footer;

    public TbConfiguration(TbConfiguration other) {
        this.font = new TbFontConfig(other.font);
        this.text = new TbTextConfig(other.text);
        this.imageText = new TbTextConfig(other.imageText);
        this.sideImageLine = new TbSideImageLineConfig(other.sideImageLine);
        this.button = new TbButtonConfig(other.button);
        this.attribute = new TbAttributeConfig(other.attribute);
        this.box = new TbBoxConfig(other.box);
        this.table = new TbTableConfig(other.table);
        this.body = new TbBodyConfig(other.body);
        this.header = new TbHeaderConfig(other.header);
        this.content = new TbContentConfig(other.content);
        this.footer = new TbFooterConfig(other.footer);
    }
}

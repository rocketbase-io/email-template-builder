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

    /**
     * variant without the visual box-frame around the content:<br>
     * body-background is white as the content itself, the content-padding is reduced to a small horizontal spacing
     * and content plus header are aligned left instead of centered - like a personally written email.<br>
     * useful for example for invitations or signature-like emails that shouldn't look like a boxed transactional email<br>
     * every setting can be changed afterwards - for example content.align back to center
     */
    public static final TbConfiguration newInstanceFrameless() {
        TbConfiguration configuration = new TbConfiguration(DEFAULT);
        configuration.getBody().setBackground(configuration.getContent().getBackground());
        configuration.getContent().setPadding("0 12px");
        configuration.getContent().setAlign("left");
        configuration.getHeader().setAlign("left");
        return configuration;
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

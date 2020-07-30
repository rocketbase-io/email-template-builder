package io.rocketbase.mail;

import io.rocketbase.mail.EmailTemplateBuilder.EmailTemplateConfigBuilder;
import io.rocketbase.mail.config.base.TbFont;
import io.rocketbase.mail.model.HtmlTextEmail;
import io.rocketbase.mail.styling.Alignment;
import io.rocketbase.mail.styling.FontStyle;
import io.rocketbase.mail.styling.FontWeight;
import io.rocketbase.mail.styling.TextDecoration;
import lombok.AccessLevel;
import lombok.Getter;

@Getter
public class TextLine implements TemplateLine {

    String text;
    String linkUrl;
    // style block
    Alignment alignment;
    FontStyle fontStyle;
    FontWeight fontWeight;
    String fontSize;
    TextDecoration textDecoration;
    String color;

    @Getter(AccessLevel.PRIVATE)
    EmailTemplateBuilder.EmailTemplateConfigBuilder builder;

    TextLine(EmailTemplateBuilder.EmailTemplateConfigBuilder builder, String text) {
        this.builder = builder;
        this.text = text;
    }

    public TextLine linkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
        return this;
    }

    public TextLine alignment(Alignment alignment) {
        this.alignment = alignment;
        return this;
    }

    public TextLine center() {
        this.alignment = Alignment.CENTER;
        return this;
    }

    public TextLine right() {
        this.alignment = Alignment.RIGHT;
        return this;
    }

    public TextLine fontStyle(FontStyle fontStyle) {
        this.fontStyle = fontStyle;
        return this;
    }

    public TextLine italic() {
        this.fontStyle = FontStyle.ITALIC;
        return this;
    }

    public TextLine fontWeight(FontWeight fontWeight) {
        this.fontWeight = fontWeight;
        return this;
    }

    public TextLine bold() {
        this.fontWeight = FontWeight.BOLD;
        return this;
    }

    public TextLine lighter() {
        this.fontWeight = FontWeight.LIGHTER;
        return this;
    }

    public TextLine textDecoration(TextDecoration textDecoration) {
        this.textDecoration = textDecoration;
        return this;
    }

    public TextLine underline() {
        this.textDecoration = TextDecoration.UNDERLINE;
        return this;
    }

    public TextLine overline() {
        this.textDecoration = TextDecoration.OVERLINE;
        return this;
    }

    public TextLine lineThrough() {
        this.textDecoration = TextDecoration.LINE_THROUGH;
        return this;
    }

    public TextLine fontSize(String fontSize) {
        this.fontSize = fontSize;
        return this;
    }

    public TextLine tbFont(TbFont tbFont) {
        this.fontSize = tbFont.getSize();
        this.color = tbFont.getColor();
        return this;
    }

    public TextLine h1() {
        tbFont(builder.getConfiguration().getFont().getH1());
        this.fontWeight = FontWeight.BOLD;
        return this;
    }

    public TextLine h2() {
        tbFont(builder.getConfiguration().getFont().getH2());
        this.fontWeight = FontWeight.BOLD;
        return this;
    }

    public TextLine h3() {
        tbFont(builder.getConfiguration().getFont().getH3());
        this.fontWeight = FontWeight.BOLD;
        return this;
    }

    public TextLine sub() {
        tbFont(builder.getConfiguration().getFont().getSub());
        return this;
    }

    /**
     * color with starting # or valid style-color lige rgb()
     */
    public TextLine color(String color) {
        this.color = color;
        return this;
    }

    @Override
    public TemplateLineType getType() {
        return TemplateLineType.TEXT;
    }

    @Override
    public EmailTemplateConfigBuilder and() {
        return builder;
    }

    @Override
    public HtmlTextEmail build() {
        return builder.build();
    }
}

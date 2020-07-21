package io.rocketbase.mail;

import io.rocketbase.mail.EmailTemplateBuilder.EmailTemplateConfigBuilder;
import io.rocketbase.mail.model.HtmlTextEmail;
import io.rocketbase.mail.styling.Alignment;
import lombok.AccessLevel;
import lombok.Getter;

@Getter
public class ImageLine implements TemplateLine {

    final String src;
    String alt;
    String width;
    String height;
    String title;
    String linkUrl;
    String margin;
    Alignment alignment;

    @Getter(AccessLevel.PRIVATE)
    EmailTemplateConfigBuilder builder;

    ImageLine(EmailTemplateConfigBuilder builder, String src) {
        this.builder = builder;

        this.src = src;
    }

    public ImageLine alt(String alt) {
        this.alt = alt;
        return this;
    }

    public ImageLine title(String title) {
        this.title = title;
        return this;
    }

    public ImageLine linkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
        return this;
    }

    public ImageLine alignment(Alignment alignment) {
        this.alignment = alignment;
        return this;
    }

    public ImageLine left() {
        this.alignment = Alignment.LEFT;
        return this;
    }

    public ImageLine center() {
        this.alignment = Alignment.CENTER;
        return this;
    }

    public ImageLine right() {
        this.alignment = Alignment.RIGHT;
        return this;
    }

    /**
     * width in pixel
     */
    public ImageLine width(int width) {
        this.width = String.valueOf(width);
        return this;
    }

    /**
     * height in pixel
     */
    public ImageLine height(int height) {
        this.height = String.valueOf(height);
        return this;
    }

    /**
     * width in percentage please add %
     */
    public ImageLine width(String width) {
        this.width = width;
        return this;
    }

    /**
     * height in percentage please add %
     */
    public ImageLine height(String height) {
        this.height = height;
        return this;
    }

    /**
     * default 20px auto
     */
    public ImageLine margin(String margin) {
        this.margin = margin;
        return this;
    }

    @Override
    public TemplateLineType getType() {
        return TemplateLineType.IMAGE;
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

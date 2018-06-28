package io.rocketbase.commons.email;

import io.rocketbase.commons.email.EmailTemplateBuilder.EmailTemplateConfigBuilder;
import io.rocketbase.commons.email.model.HtmlTextEmail;
import io.rocketbase.commons.email.template.Alignment;
import io.rocketbase.commons.email.template.TemplateLine;
import lombok.AccessLevel;
import lombok.Getter;

@Getter
public class ImageLine implements TemplateLine {

    final String src;
    final String alt;
    final int width;
    final int height;
    String title;
    String linkUrl;
    Alignment alignment = Alignment.CENTER;

    @Getter(AccessLevel.PRIVATE)
    EmailTemplateConfigBuilder builder;

    ImageLine(EmailTemplateConfigBuilder builder, String src, String alt, int width, int height) {
        this.builder = builder;

        this.src = src;
        this.alt = alt;
        this.width = width;
        this.height = height;
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

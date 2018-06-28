package io.rocketbase.commons.email;

import io.rocketbase.commons.email.EmailTemplateBuilder.EmailTemplateConfigBuilder;
import io.rocketbase.commons.email.model.HtmlTextEmail;
import io.rocketbase.commons.email.template.Alignment;
import io.rocketbase.commons.email.template.ColorStyle;
import lombok.AccessLevel;
import lombok.Getter;

@Getter
public class HeaderConfig {

    final String title;
    ColorStyle color = ColorStyle.BASE_STYLE;
    Alignment alignment = Alignment.CENTER;

    @Getter(AccessLevel.PRIVATE)
    EmailTemplateConfigBuilder builder;

    HeaderConfig(EmailTemplateConfigBuilder builder, String title) {
        this.builder = builder;

        this.title = title;
    }

    public HeaderConfig color(ColorStyle color) {
        this.color = color;
        return this;
    }

    public HeaderConfig alignment(Alignment alignment) {
        this.alignment = alignment;
        return this;
    }

    public HeaderConfig left() {
        this.alignment = Alignment.LEFT;
        return this;
    }

    public HeaderConfig center() {
        this.alignment = Alignment.CENTER;
        return this;
    }

    public HeaderConfig right() {
        this.alignment = Alignment.RIGHT;
        return this;
    }

    public EmailTemplateConfigBuilder and() {
        return builder;
    }

    public HtmlTextEmail build() {
        return builder.build();
    }

}

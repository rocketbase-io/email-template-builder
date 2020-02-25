package io.rocketbase.commons.email;

import io.rocketbase.commons.email.EmailTemplateBuilder.EmailTemplateConfigBuilder;
import io.rocketbase.commons.email.model.HtmlTextEmail;
import io.rocketbase.commons.email.template.Alignment;
import io.rocketbase.commons.email.template.ColorPalette;
import io.rocketbase.commons.email.template.ColorStyle;
import io.rocketbase.commons.email.template.TemplateLine;
import lombok.AccessLevel;
import lombok.Getter;

@Getter
public class ButtonLine implements TemplateLine {

    final String text;
    final String url;
    ColorStyle color = ColorStyle.BASE_STYLE;
    Alignment alignment = Alignment.CENTER;

    @Getter(AccessLevel.PRIVATE)
    EmailTemplateConfigBuilder builder;

    ButtonLine(EmailTemplateConfigBuilder builder, String text, String url) {
        this.builder = builder;

        this.text = text;
        this.url = url;
    }

    public ButtonLine color(ColorStyle color) {
        this.color = color;
        return this;
    }

    public ButtonLine color(ColorPalette colorPalette) {
        this.color = new ColorStyle(colorPalette);
        return this;
    }

    public ButtonLine alignment(Alignment alignment) {
        this.alignment = alignment;
        return this;
    }

    public ButtonLine left() {
        this.alignment = Alignment.LEFT;
        return this;
    }

    public ButtonLine center() {
        this.alignment = Alignment.CENTER;
        return this;
    }

    public ButtonLine right() {
        this.alignment = Alignment.RIGHT;
        return this;
    }

    @Override
    public TemplateLineType getType() {
        return TemplateLineType.BUTTON;
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

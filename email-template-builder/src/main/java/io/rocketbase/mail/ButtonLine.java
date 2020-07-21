package io.rocketbase.mail;

import io.rocketbase.commons.colors.ColorPalette;
import io.rocketbase.mail.model.HtmlTextEmail;
import io.rocketbase.mail.styling.Alignment;
import io.rocketbase.mail.styling.ColorStyleSimple;
import lombok.AccessLevel;
import lombok.Getter;

@Getter
public class ButtonLine implements TemplateLine {

    final String text;
    final String url;
    ColorStyleSimple color = ColorStyleSimple.BASE_STYLE;
    Alignment alignment = Alignment.CENTER;

    @Getter(AccessLevel.PRIVATE)
    EmailTemplateBuilder.EmailTemplateConfigBuilder builder;

    ButtonLine(EmailTemplateBuilder.EmailTemplateConfigBuilder builder, String text, String url) {
        this.builder = builder;

        this.text = text;
        this.url = url;
    }

    public ButtonLine color(ColorStyleSimple color) {
        this.color = color;
        return this;
    }

    public ButtonLine color(ColorPalette colorPalette) {
        this.color = new ColorStyleSimple(colorPalette);
        return this;
    }

    public ButtonLine blue() {
        this.color = ColorStyleSimple.BLUE_STYLE;
        return this;
    }

    public ButtonLine green() {
        this.color = ColorStyleSimple.GREEN_STYLE;
        return this;
    }

    public ButtonLine red() {
        this.color = ColorStyleSimple.RED_STYLE;
        return this;
    }

    public ButtonLine yellow() {
        this.color = ColorStyleSimple.YELLOW_STYLE;
        return this;
    }

    public ButtonLine black() {
        this.color = ColorStyleSimple.BLACK_STYLE;
        return this;
    }

    public ButtonLine gray() {
        this.color = ColorStyleSimple.GRAY_STYLE;
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
    public EmailTemplateBuilder.EmailTemplateConfigBuilder and() {
        return builder;
    }

    @Override
    public HtmlTextEmail build() {
        return builder.build();
    }
}

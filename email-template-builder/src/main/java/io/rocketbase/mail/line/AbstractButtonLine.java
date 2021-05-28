package io.rocketbase.mail.line;

import io.rocketbase.commons.colors.ColorPalette;
import io.rocketbase.mail.styling.Alignment;
import io.rocketbase.mail.styling.ColorStyle;
import io.rocketbase.mail.styling.ColorStyleSimple;
import lombok.Getter;

@Getter
public class AbstractButtonLine<E extends AbstractButtonLine> {

    final String text;
    final String url;
    ColorStyle color = ColorStyleSimple.BASE_STYLE;
    Alignment alignment = Alignment.CENTER;

    public AbstractButtonLine( String text, String url) {
        this.text = text;
        this.url = url;
    }

    public E color(ColorStyle color) {
        this.color = color;
        return (E) this;
    }

    public E color(ColorPalette colorPalette) {
        this.color = new ColorStyleSimple(colorPalette);
        return (E) this;
    }

    public E blue() {
        this.color = ColorStyleSimple.BLUE_STYLE;
        return (E) this;
    }

    public E green() {
        this.color = ColorStyleSimple.GREEN_STYLE;
        return (E) this;
    }

    public E red() {
        this.color = ColorStyleSimple.RED_STYLE;
        return (E) this;
    }

    public E yellow() {
        this.color = ColorStyleSimple.YELLOW_STYLE;
        return (E) this;
    }

    public E black() {
        this.color = ColorStyleSimple.BLACK_STYLE;
        return (E) this;
    }

    public E gray() {
        this.color = ColorStyleSimple.GRAY_STYLE;
        return (E) this;
    }

    public E alignment(Alignment alignment) {
        this.alignment = alignment;
        return (E) this;
    }

    public E left() {
        this.alignment = Alignment.LEFT;
        return (E) this;
    }

    public E center() {
        this.alignment = Alignment.CENTER;
        return (E) this;
    }

    public E right() {
        this.alignment = Alignment.RIGHT;
        return (E) this;
    }
}

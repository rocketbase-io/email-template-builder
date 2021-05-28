package io.rocketbase.mail.line;

import io.rocketbase.mail.config.base.TbFont;
import io.rocketbase.mail.styling.Alignment;
import io.rocketbase.mail.styling.FontStyle;
import io.rocketbase.mail.styling.FontWeight;
import io.rocketbase.mail.styling.TextDecoration;
import lombok.Getter;

@Getter
public class AbstractTextLine<E extends AbstractTextLine> {

    protected String text;
    protected String linkUrl;
    // style block
    protected Alignment alignment;
    protected FontStyle fontStyle;
    protected FontWeight fontWeight;
    protected String fontSize;
    protected TextDecoration textDecoration;
    protected String color;

    public AbstractTextLine(String text) {
        this.text = text;
    }

    public E linkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
        return (E) this;
    }

    public E alignment(Alignment alignment) {
        this.alignment = alignment;
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

    public E fontStyle(FontStyle fontStyle) {
        this.fontStyle = fontStyle;
        return (E) this;

    }

    public E italic() {
        this.fontStyle = FontStyle.ITALIC;
        return (E) this;

    }

    public E fontWeight(FontWeight fontWeight) {
        this.fontWeight = fontWeight;
        return (E) this;

    }

    public E bold() {
        this.fontWeight = FontWeight.BOLD;
        return (E) this;

    }

    public E lighter() {
        this.fontWeight = FontWeight.LIGHTER;
        return (E) this;

    }

    public E textDecoration(TextDecoration textDecoration) {
        this.textDecoration = textDecoration;
        return (E) this;

    }

    public E underline() {
        this.textDecoration = TextDecoration.UNDERLINE;
        return (E) this;

    }

    public E overline() {
        this.textDecoration = TextDecoration.OVERLINE;
        return (E) this;

    }

    public E lineThrough() {
        this.textDecoration = TextDecoration.LINE_THROUGH;
        return (E) this;

    }

    public E fontSize(String fontSize) {
        this.fontSize = fontSize;
        return (E) this;

    }

    public E tbFont(TbFont tbFont) {
        this.fontSize = tbFont.getSize();
        this.color = tbFont.getColor();
        return (E) this;

    }

    /**
     * color with starting # or valid style-color lige rgb()
     */
    public E color(String color) {
        this.color = color;
        return (E) this;

    }
}

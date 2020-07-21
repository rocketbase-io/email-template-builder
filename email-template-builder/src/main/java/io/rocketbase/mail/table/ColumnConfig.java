package io.rocketbase.mail.table;

import io.rocketbase.mail.styling.Alignment;
import io.rocketbase.mail.styling.FontStyle;
import io.rocketbase.mail.styling.FontWeight;
import io.rocketbase.mail.styling.TextDecoration;
import lombok.Getter;

@Getter
public class ColumnConfig {

    String width;
    Alignment alignment;
    int colspan = 1;

    FontStyle fontStyle;
    FontWeight fontWeight;
    TextDecoration textDecoration;

    /**
     * java.text.DecimalFormat
     */
    String numberFormat;

    /**
     * width in pixel
     */
    public ColumnConfig width(int width) {
        this.width = String.valueOf(width);
        return this;
    }

    /**
     * width in percentage please add %
     */
    public ColumnConfig width(String width) {
        this.width = width;
        return this;
    }

    public ColumnConfig colspan(int colspan) {
        this.colspan = colspan;
        return this;
    }

    public ColumnConfig alignment(Alignment alignment) {
        this.alignment = alignment;
        return this;
    }

    public ColumnConfig left() {
        this.alignment = Alignment.LEFT;
        return this;
    }

    public ColumnConfig center() {
        this.alignment = Alignment.CENTER;
        return this;
    }

    public ColumnConfig right() {
        this.alignment = Alignment.RIGHT;
        return this;
    }

    public ColumnConfig fontStyle(FontStyle fontStyle) {
        this.fontStyle = fontStyle;
        return this;
    }

    public ColumnConfig italic() {
        this.fontStyle = FontStyle.ITALIC;
        return this;
    }

    public ColumnConfig fontWeight(FontWeight fontWeight) {
        this.fontWeight = fontWeight;
        return this;
    }

    public ColumnConfig bold() {
        this.fontWeight = FontWeight.BOLD;
        return this;
    }

    public ColumnConfig lighter() {
        this.fontWeight = FontWeight.LIGHTER;
        return this;
    }

    public ColumnConfig textDecoration(TextDecoration textDecoration) {
        this.textDecoration = textDecoration;
        return this;
    }

    public ColumnConfig underline() {
        this.textDecoration = TextDecoration.UNDERLINE;
        return this;
    }

    public ColumnConfig overline() {
        this.textDecoration = TextDecoration.OVERLINE;
        return this;
    }

    public ColumnConfig lineThrough() {
        this.textDecoration = TextDecoration.LINE_THROUGH;
        return this;
    }

    public ColumnConfig numberFormat(String numberFormat) {
        this.numberFormat = numberFormat;
        return this;
    }

}

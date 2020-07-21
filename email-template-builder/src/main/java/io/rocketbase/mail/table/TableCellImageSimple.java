package io.rocketbase.mail.table;

import lombok.Getter;

@Getter
public class TableCellImageSimple implements TableCellImage {

    final String src;
    String alt;
    String width;
    String height;
    String title;
    String linkUrl;

    public TableCellImageSimple(String src) {
        this.src = src;
    }

    public TableCellImage alt(String alt) {
        this.alt = alt;
        return this;
    }

    public TableCellImage title(String title) {
        this.title = title;
        return this;
    }

    public TableCellImage linkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
        return this;
    }


    /**
     * width in pixel
     */
    public TableCellImage width(int width) {
        this.width = String.valueOf(width);
        return this;
    }

    /**
     * height in pixel
     */
    public TableCellImage height(int height) {
        this.height = String.valueOf(height);
        return this;
    }

    /**
     * width in percentage please add %
     */
    public TableCellImage width(String width) {
        this.width = width;
        return this;
    }

    /**
     * height in percentage please add %
     */
    public TableCellImage height(String height) {
        this.height = height;
        return this;
    }
}

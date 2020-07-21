package io.rocketbase.mail.table;

import lombok.Getter;

@Getter
public class TableCellLinkSimple implements TableCellLink {

    final String text;
    final String linkUrl;

    public TableCellLinkSimple(String text, String linkUrl) {
        this.text = text;
        this.linkUrl = linkUrl;
    }
}

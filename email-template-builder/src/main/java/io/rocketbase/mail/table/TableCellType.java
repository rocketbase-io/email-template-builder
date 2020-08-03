package io.rocketbase.mail.table;

public enum TableCellType {

    /**
     * will work in header-, item- and footer-row
     */
    LINK,

    /**
     * will only work in item-row
     */
    IMAGE,

    /**
     * will work in header-, item- and footer-row
     */
    HTML;
}

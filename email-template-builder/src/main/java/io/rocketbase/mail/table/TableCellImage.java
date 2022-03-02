package io.rocketbase.mail.table;

public interface TableCellImage extends TableCell {

    String getSrc();

    String getSrcDark();

    String getAlt();

    String getWidth();

    String getHeight();

    String getTitle();

    String getLinkUrl();

    default TableCellType getType() {
        return TableCellType.IMAGE;
    }
}

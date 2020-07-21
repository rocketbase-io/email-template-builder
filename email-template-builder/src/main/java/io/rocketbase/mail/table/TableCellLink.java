package io.rocketbase.mail.table;

public interface TableCellLink extends TableCell {
    String getText();

    String getLinkUrl();

    default TableCellType getType() {
        return TableCellType.LINK;
    }
}

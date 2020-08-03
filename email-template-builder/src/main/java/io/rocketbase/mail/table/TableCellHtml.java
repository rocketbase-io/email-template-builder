package io.rocketbase.mail.table;

public interface TableCellHtml extends TableCell {

    String getHtml();

    String getText();

    default TableCellType getType() {
        return TableCellType.HTML;
    }
}

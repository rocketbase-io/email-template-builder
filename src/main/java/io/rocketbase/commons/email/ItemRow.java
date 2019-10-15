package io.rocketbase.commons.email;

import io.rocketbase.commons.email.template.Alignment;
import io.rocketbase.commons.email.template.RowLine;

public class ItemRow implements RowLine {

    final TableConfig tableConfig;
    private String product;
    private String price;

    private Alignment alignment = Alignment.CENTER;


    public ItemRow(TableConfig tableConfig, String product, String price) {
        this.product = product;
        this.price = price;
        this.tableConfig = tableConfig;
    }


    @Override
    public RowLineType getType() {
        return RowLineType.ITEM;
    }

    @Override
    public TableConfig nextRow() {
        return tableConfig;
    }

    @Override
    public EmailTemplateBuilder.EmailTemplateConfigBuilder and() {
        return tableConfig.and();
    }

    @Override
    public TableConfig alignment(Alignment alignment) {
        this.alignment = alignment;
        return tableConfig;
    }

}

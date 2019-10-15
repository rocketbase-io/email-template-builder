package io.rocketbase.commons.email;

import io.rocketbase.commons.email.template.Alignment;
import io.rocketbase.commons.email.template.RowLine;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class TotalRow implements RowLine {

    final TableConfig tableConfig;
    @Getter(AccessLevel.PRIVATE)
    EmailTemplateBuilder.EmailTemplateConfigBuilder builder;
    private String price;
    private Alignment alignment = Alignment.CENTER;

    public TotalRow(TableConfig tableConfig, String price) {
        this.tableConfig = tableConfig;
        this.price = price;
    }

    @Override
    public RowLineType getType() {
        return RowLineType.TOTAL;
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

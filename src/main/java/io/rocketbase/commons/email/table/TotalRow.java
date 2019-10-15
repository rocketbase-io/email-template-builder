package io.rocketbase.commons.email.table;

import io.rocketbase.commons.email.TableConfig;
import io.rocketbase.commons.email.template.AbstractRowLine;
import io.rocketbase.commons.email.template.Alignment;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class TotalRow extends AbstractRowLine<ItemRow> {

    String total = "Total";

    public TotalRow(TableConfig tableConfig, BigDecimal priceValue) {
        super(RowLineType.TOTAL, tableConfig, null, priceValue);
        alignmentFirstColumn(Alignment.RIGHT);
    }

    public TotalRow(TableConfig tableConfig, String price) {
        super(RowLineType.TOTAL, tableConfig, price, null);
        alignmentFirstColumn(Alignment.RIGHT);
    }

    public TotalRow totalCaption(String total) {
        this.total = total;
        return this;
    }

}

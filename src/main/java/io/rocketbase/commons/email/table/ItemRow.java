package io.rocketbase.commons.email.table;

import io.rocketbase.commons.email.TableConfig;
import io.rocketbase.commons.email.template.AbstractRowLine;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class ItemRow extends AbstractRowLine<ItemRow> {

    String product;

    public ItemRow(TableConfig tableConfig, String product, BigDecimal priceValue) {
        super(RowLineType.ITEM, tableConfig, null, priceValue);
        this.product = product;
    }

    public ItemRow(TableConfig tableConfig, String product, String price) {
        super(RowLineType.ITEM, tableConfig, price, null);
        this.product = product;
    }

}

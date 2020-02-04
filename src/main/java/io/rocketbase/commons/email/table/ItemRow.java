package io.rocketbase.commons.email.table;

import io.rocketbase.commons.email.TableConfig;
import io.rocketbase.commons.email.template.AbstractRowLine;
import io.rocketbase.commons.email.template.Alignment;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class ItemRow extends AbstractRowLine<ItemRow> {


    String prefix;
    String product;
    String middle;

    Alignment alignPrefixColumn = Alignment.LEFT;
    Alignment alignMiddleColumn = Alignment.RIGHT;

    boolean headerRow;

    public ItemRow(TableConfig tableConfig, String prefix, String product, String middle, BigDecimal priceValue) {
        super(RowLineType.ITEM, tableConfig, null, priceValue);
        this.prefix = prefix;
        this.product = product;
        this.middle = middle;
    }

    public ItemRow(TableConfig tableConfig, String prefix, String product, String middle, String price) {
        super(RowLineType.ITEM, tableConfig, price, null);
        this.prefix = prefix;
        this.product = product;
        this.middle = middle;
    }

    public ItemRow alignMiddleColumn(Alignment alignment) {
        this.alignMiddleColumn = alignment;
        return this;
    }

    public ItemRow alignPrefixColumn(Alignment alignment) {
        this.alignPrefixColumn = alignment;
        return this;
    }

    /**
     * mark text bold + border-bottom 2px
     * @return
     */
    public ItemRow headerRow() {
        this.headerRow = true;
        return this;
    }

}

package io.rocketbase.commons.email.template;

import io.rocketbase.commons.email.EmailTemplateBuilder;
import io.rocketbase.commons.email.TableConfig;
import io.rocketbase.commons.email.model.HtmlTextEmail;
import lombok.Getter;

import java.math.BigDecimal;
import java.text.DecimalFormat;

@Getter
public abstract class AbstractRowLine<T extends RowLine> implements RowLine {

    final TableConfig tableConfig;
    final RowLineType type;

    BigDecimal priceValue;
    String price;

    Alignment alignmentPrice = Alignment.RIGHT;

    Alignment alignmentFirstColumn = Alignment.LEFT;

    protected AbstractRowLine(RowLineType type, TableConfig tableConfig, String price, BigDecimal priceValue) {
        this.type = type;
        this.tableConfig = tableConfig;
        this.price = price;
        this.priceValue = priceValue;
    }

    public RowLine.RowLineType getType() {
        return type;
    }

    public T alignmentPrice(Alignment alignment) {
        this.alignmentPrice = alignment;
        return (T) this;
    }

    public T alignmentFirstColumn(Alignment alignment) {
        this.alignmentFirstColumn = alignment;
        return (T) this;
    }

    public TableConfig nextRow() {
        return tableConfig;
    }

    public EmailTemplateBuilder.EmailTemplateConfigBuilder and() {
        return tableConfig.and();
    }

    public HtmlTextEmail build() {
        return tableConfig.build();
    }

    public void formatPrice(DecimalFormat decimalFormat) {
        if (priceValue != null && decimalFormat != null) {
            this.price = decimalFormat.format(priceValue);
        }
    }
}

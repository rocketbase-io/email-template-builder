package io.rocketbase.commons.email;

import io.rocketbase.commons.email.EmailTemplateBuilder.EmailTemplateConfigBuilder;
import io.rocketbase.commons.email.model.HtmlTextEmail;
import io.rocketbase.commons.email.table.ItemRow;
import io.rocketbase.commons.email.table.TotalRow;
import io.rocketbase.commons.email.template.Alignment;
import io.rocketbase.commons.email.template.RowLine;
import io.rocketbase.commons.email.template.TableWidthUnit;
import io.rocketbase.commons.email.template.TemplateLine;
import lombok.AccessLevel;
import lombok.Getter;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Getter
public class TableConfig implements TemplateLine {
    List<RowLine> rows = new ArrayList<>();

    @Getter(AccessLevel.PRIVATE)
    EmailTemplateConfigBuilder builder;

    DecimalFormat decimalFormat = new DecimalFormat("0.00 '€'", DecimalFormatSymbols.getInstance(Locale.GERMAN));

    TextLine headerLine;
    String tableWidth = "95%";
    String firstColumnWidth = "80%";
    String prefixWidth;
    String middleWidth;
    String priceWidth;
    boolean hasPrefixColumn;
    boolean hasMiddleColumn;


    TableConfig(EmailTemplateConfigBuilder builder) {
        this.builder = builder;
    }

    public TableConfig addHeader(String text, boolean asHtml, Alignment alignment) {
        headerLine = new TextLine(TemplateLineType.TEXT, null, text, asHtml);
        if (alignment != null) {
            headerLine.alignment(alignment);
        }
        return this;
    }

    public ItemRow addItemRow(String product, String price) {
        ItemRow itemRow = new ItemRow(this, null, product, null, price);
        rows.add(itemRow);
        return itemRow;
    }

    public ItemRow addItemRowWithPrefix(String prefix, String product, String price) {
        ItemRow itemRow = new ItemRow(this, prefix, product, null, price);
        rows.add(itemRow);
        handlePrefix();
        return itemRow;
    }

    public ItemRow addItemRowWithPrefix(Number prefix, String product, String price) {
        ItemRow itemRow = new ItemRow(this, String.valueOf(prefix), product, null, price);
        rows.add(itemRow);
        handlePrefix();
        return itemRow;
    }

    public ItemRow addItemRow(String product, BigDecimal price) {
        ItemRow itemRow = new ItemRow(this, null, product, null, price);
        rows.add(itemRow);
        return itemRow;
    }

    public ItemRow addItemRowWithPrefix(String prefix, String product, BigDecimal price) {
        ItemRow itemRow = new ItemRow(this, prefix, product, null, price);
        rows.add(itemRow);
        handlePrefix();
        return itemRow;
    }

    public ItemRow addItemRowWithPrefix(Number prefix, String product, BigDecimal price) {
        ItemRow itemRow = new ItemRow(this, String.valueOf(prefix), product, null, price);
        rows.add(itemRow);
        handlePrefix();
        return itemRow;
    }

    public ItemRow addItemRowWithMiddle(String product, String middle, String price) {
        ItemRow itemRow = new ItemRow(this, null, product, middle, price);
        rows.add(itemRow);

        handleMiddle();
        return itemRow;
    }

    public ItemRow addItemRowWithPrefixMiddle(String prefix, String product, String middle, String price) {
        ItemRow itemRow = new ItemRow(this, prefix, product, middle, price);
        rows.add(itemRow);

        handlePrefix();
        handleMiddle();
        return itemRow;
    }

    public ItemRow addItemRowWithPrefixMiddle(Number prefix, String product, String middle, String price) {
        ItemRow itemRow = new ItemRow(this, String.valueOf(prefix), product, middle, price);
        rows.add(itemRow);

        handlePrefix();
        handleMiddle();
        return itemRow;
    }

    public ItemRow addItemRowWithMiddle(String product, String middle, BigDecimal price) {
        ItemRow itemRow = new ItemRow(this, null, product, middle, price);
        rows.add(itemRow);
        handleMiddle();
        return itemRow;
    }

    public ItemRow addItemRowWithPrefixMiddle(String prefix, String product, String middle, BigDecimal price) {
        ItemRow itemRow = new ItemRow(this, prefix, product, middle, price);
        rows.add(itemRow);

        handlePrefix();
        handleMiddle();
        return itemRow;
    }

    public ItemRow addItemRowWithPrefixMiddle(Number prefix, String product, String middle, BigDecimal price) {
        ItemRow itemRow = new ItemRow(this, String.valueOf(prefix), product, middle, price);
        rows.add(itemRow);

        handlePrefix();
        handleMiddle();
        return itemRow;
    }

    private void handleMiddle() {
        if (!hasMiddleColumn) {
            // first time change first column width for better fitting
            firstColumnWidth = null;
        }
        hasMiddleColumn = true;
    }

    private void handlePrefix() {
        if (!hasPrefixColumn) {
            // first time change first column width for better fitting
            firstColumnWidth = "80%";
        }
        hasPrefixColumn = true;
    }

    public TotalRow addTotalRow(String price) {
        TotalRow totalRow = new TotalRow(this, price);
        rows.add(totalRow);
        return totalRow;
    }

    public TotalRow addTotalRow(BigDecimal price) {
        TotalRow totalRow = new TotalRow(this, price);
        rows.add(totalRow);
        return totalRow;
    }

    /**
     * you can configure custom decimal formats like<br>
     * new DecimalFormat("0.00", DecimalFormatSymbols.getInstance(Locale.GERMANY))
     */
    public TableConfig decimalFormat(DecimalFormat decimalFormat) {
        this.decimalFormat = decimalFormat;
        return this;
    }

    public TableConfig decimalFormatUS() {
        this.decimalFormat = new DecimalFormat("'$' 0.00", DecimalFormatSymbols.getInstance(Locale.US));
        return this;
    }

    public TableConfig widthPrefixColumnClear(int value, TableWidthUnit unit) {
        this.prefixWidth = unit.getHtmlValue(value);
        return this;
    }

    public TableConfig widthPrefixColumnClear() {
        this.prefixWidth = null;
        return this;
    }

    public TableConfig widthFirstColumn(int value, TableWidthUnit unit) {
        this.firstColumnWidth = unit.getHtmlValue(value);
        return this;
    }

    public TableConfig widthFirstColumnClear() {
        this.firstColumnWidth = null;
        return this;
    }

    public TableConfig widthMiddleColumn(int value, TableWidthUnit unit) {
        this.middleWidth = unit.getHtmlValue(value);
        return this;
    }

    public TableConfig widthMiddleColumnClear() {
        this.middleWidth = null;
        return this;
    }

    public TableConfig widthPriceColumn(int value, TableWidthUnit unit) {
        this.priceWidth = unit.getHtmlValue(value);
        return this;
    }

    public TableConfig widthPriceColumnClear() {
        this.priceWidth = null;
        return this;
    }

    /**
     * you can specify table, with = 95% is default
     */
    public TableConfig widthTable(int value, TableWidthUnit unit) {
        this.tableWidth = unit.getHtmlValue(value);
        return this;
    }

    @Override
    public TemplateLineType getType() {
        return TemplateLineType.TABLE;
    }

    private void formatPrices() {
        if (decimalFormat != null) {
            rows.forEach(r -> r.formatPrice(decimalFormat));
        }
    }

    @Override
    public EmailTemplateConfigBuilder and() {
        formatPrices();
        return builder;
    }

    @Override
    public HtmlTextEmail build() {
        formatPrices();
        return builder.build();
    }

}

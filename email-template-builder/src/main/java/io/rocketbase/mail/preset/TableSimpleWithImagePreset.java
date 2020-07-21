package io.rocketbase.mail.preset;

import io.rocketbase.mail.EmailTemplateBuilder.EmailTemplateConfigBuilder;
import io.rocketbase.mail.TableLine;
import io.rocketbase.mail.model.HtmlTextEmail;
import io.rocketbase.mail.styling.Alignment;
import io.rocketbase.mail.table.ColumnConfig;
import io.rocketbase.mail.table.TableCellImageSimple;
import lombok.AccessLevel;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
public class TableSimpleWithImagePreset implements TableLine {

    final String numberFormat;

    List<List<Object>> headerRows = new ArrayList<>();
    List<List<Object>> itemRows = new ArrayList<>();
    List<List<Object>> footerRows = new ArrayList<>();

    @Getter(AccessLevel.PRIVATE)
    EmailTemplateConfigBuilder builder;

    public TableSimpleWithImagePreset(EmailTemplateConfigBuilder builder, String numberFormat) {
        this.builder = builder;
        this.numberFormat = numberFormat;
    }

    @Override
    public EmailTemplateConfigBuilder and() {
        return builder;
    }

    @Override
    public HtmlTextEmail build() {
        return builder.build();
    }

    public TableSimpleWithImagePreset headerRow(String imageName, String descriptionName, String amountName) {
        headerRows.add(Arrays.asList(imageName, descriptionName, amountName));
        return this;
    }

    public TableSimpleWithImagePreset itemRow(String imageUrl, String description, BigDecimal amount) {
        itemRows.add(Arrays.asList(new TableCellImageSimple(imageUrl), description, amount));
        return this;
    }

    public TableSimpleWithImagePreset footerRow(String totalName, BigDecimal amount) {
        footerRows.add(Arrays.asList(totalName, amount));
        return this;
    }

    @Override
    public List<ColumnConfig> getHeader() {
        return Arrays.asList(new ColumnConfig()
                        .width("20%"),
                new ColumnConfig()
                        .width("60%"),
                new ColumnConfig()
                        .alignment(Alignment.RIGHT)
                        .width("20%"));
    }

    @Override
    public List<ColumnConfig> getItem() {
        return Arrays.asList(new ColumnConfig(),
                new ColumnConfig(),
                new ColumnConfig()
                        .alignment(Alignment.RIGHT)
                        .numberFormat(numberFormat));
    }

    @Override
    public List<ColumnConfig> getFooter() {
        return Arrays.asList(new ColumnConfig()
                        .colspan(2)
                        .alignment(Alignment.RIGHT),
                new ColumnConfig()
                        .alignment(Alignment.RIGHT)
                        .numberFormat(numberFormat));
    }
}

package io.rocketbase.mail.preset;

import io.rocketbase.mail.EmailTemplateBuilder.EmailTemplateConfigBuilder;
import io.rocketbase.mail.TableLine;
import io.rocketbase.mail.model.HtmlTextEmail;
import io.rocketbase.mail.styling.Alignment;
import io.rocketbase.mail.styling.FontWeight;
import io.rocketbase.mail.table.ColumnConfig;
import lombok.AccessLevel;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
public class TableSimplePreset implements TableLine {

    final String numberFormat;

    List<List<Object>> headerRows = new ArrayList<>();
    List<List<Object>> itemRows = new ArrayList<>();
    List<List<Object>> footerRows = new ArrayList<>();

    @Getter(AccessLevel.PRIVATE)
    EmailTemplateConfigBuilder builder;

    public TableSimplePreset(EmailTemplateConfigBuilder builder, String numberFormat) {
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

    public TableSimplePreset headerRow(String descriptionName, String amountName) {
        headerRows.add(Arrays.asList(descriptionName, amountName));
        return this;
    }

    public TableSimplePreset itemRow(String description, BigDecimal amount) {
        itemRows.add(Arrays.asList(description, amount));
        return this;
    }

    public TableSimplePreset footerRow(String totalName, BigDecimal amount) {
        footerRows.add(Arrays.asList(totalName, amount));
        return this;
    }

    @Override
    public List<ColumnConfig> getHeader() {
        return Arrays.asList(new ColumnConfig()
                        .width("80%"),
                new ColumnConfig()
                        .alignment(Alignment.RIGHT)
                        .width("20%"));
    }

    @Override
    public List<ColumnConfig> getItem() {
        return Arrays.asList(new ColumnConfig(),
                new ColumnConfig()
                        .alignment(Alignment.RIGHT)
                        .numberFormat(numberFormat));
    }

    @Override
    public List<ColumnConfig> getFooter() {
        return Arrays.asList(new ColumnConfig()
                        .bold()
                        .alignment(Alignment.RIGHT),
                new ColumnConfig()
                        .alignment(Alignment.RIGHT)
                        .bold()
                        .numberFormat(numberFormat));
    }
}

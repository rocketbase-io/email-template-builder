package io.rocketbase.mail.preset;

import io.rocketbase.mail.EmailTemplateBuilder.EmailTemplateConfigBuilder;
import io.rocketbase.mail.TableLine;
import io.rocketbase.mail.model.HtmlTextEmail;
import io.rocketbase.mail.styling.Alignment;
import io.rocketbase.mail.table.ColumnConfig;
import lombok.AccessLevel;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
public class TableFourColumnPreset implements TableLine {

    final String taxFormat;
    final String amountFormat;

    List<List<Object>> headerRows = new ArrayList<>();
    List<List<Object>> itemRows = new ArrayList<>();
    List<List<Object>> footerRows = new ArrayList<>();

    @Getter(AccessLevel.PRIVATE)
    EmailTemplateConfigBuilder builder;

    public TableFourColumnPreset(EmailTemplateConfigBuilder builder, String taxFormat, String amountFormat) {
        this.builder = builder;
        this.taxFormat = taxFormat;
        this.amountFormat = amountFormat;
    }

    @Override
    public EmailTemplateConfigBuilder and() {
        return builder;
    }

    @Override
    public HtmlTextEmail build() {
        return builder.build();
    }

    public TableFourColumnPreset headerRow(String quantityName, String descriptionName, String taxName, String amountName) {
        headerRows.add(Arrays.asList(quantityName, descriptionName, taxName, amountName));
        return this;
    }

    public TableFourColumnPreset itemRow(Integer quantity, String description, BigDecimal tax, BigDecimal amount) {
        itemRows.add(Arrays.asList(quantity, description, tax, amount));
        return this;
    }

    public TableFourColumnPreset footerRow(String label, BigDecimal amount) {
        footerRows.add(Arrays.asList(label, amount));
        return this;
    }

    @Override
    public List<ColumnConfig> getHeader() {
        return Arrays.asList(new ColumnConfig(),
                new ColumnConfig()
                        .width("60%"),
                new ColumnConfig()
                        .alignment(Alignment.RIGHT),
                new ColumnConfig()
                        .width("20%")
                        .alignment(Alignment.RIGHT));
    }

    @Override
    public List<ColumnConfig> getItem() {
        return Arrays.asList(new ColumnConfig(),
                new ColumnConfig(),
                new ColumnConfig()
                        .alignment(Alignment.RIGHT)
                        .numberFormat(taxFormat),
                new ColumnConfig()
                        .alignment(Alignment.RIGHT)
                        .numberFormat(amountFormat));
    }

    @Override
    public List<ColumnConfig> getFooter() {
        return Arrays.asList(new ColumnConfig()
                        .colspan(3)
                        .alignment(Alignment.RIGHT),
                new ColumnConfig()
                        .alignment(Alignment.RIGHT)
                        .numberFormat(amountFormat));
    }
}

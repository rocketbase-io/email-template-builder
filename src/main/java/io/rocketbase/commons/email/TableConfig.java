package io.rocketbase.commons.email;

import io.rocketbase.commons.email.EmailTemplateBuilder.EmailTemplateConfigBuilder;
import io.rocketbase.commons.email.model.HtmlTextEmail;
import io.rocketbase.commons.email.template.Alignment;
import io.rocketbase.commons.email.template.ColorStyle;
import io.rocketbase.commons.email.template.RowLine;
import io.rocketbase.commons.email.template.TemplateLine;
import lombok.AccessLevel;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;


public class TableConfig implements TemplateLine {

    ColorStyle color = ColorStyle.BASE_STYLE;
    Alignment alignment = Alignment.CENTER;
    List<RowLine> rows = new ArrayList<>();

    @Getter(AccessLevel.PRIVATE)
    EmailTemplateConfigBuilder builder;

    TableConfig(EmailTemplateConfigBuilder builder) {
        this.builder = builder;
    }

    public ItemRow addItemRow(String product, String price) {
        ItemRow itemRow = new ItemRow(this, product, price);
        rows.add(itemRow);
        return itemRow;
    }

    public TotalRow addToalRow(String price) {
        TotalRow totalRow = new TotalRow(this, price);
        rows.add(totalRow);
        return totalRow;
    }

    @Override
    public TemplateLineType getType() {
        return TemplateLineType.TABLE;
    }

    @Override
    public EmailTemplateConfigBuilder and() {
        return builder;
    }

    @Override
    public HtmlTextEmail build() {
        return builder.build();
    }

}

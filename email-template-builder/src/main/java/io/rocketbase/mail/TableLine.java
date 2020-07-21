package io.rocketbase.mail;

import io.rocketbase.mail.model.HtmlTextEmail;
import io.rocketbase.mail.table.ColumnConfig;

import java.util.List;

public interface TableLine extends TemplateLine {

    default TemplateLineType getType() {
        return TemplateLineType.TABLE;
    }

    List<ColumnConfig> getHeader();

    List<ColumnConfig> getItem();

    List<ColumnConfig> getFooter();

    List<List<Object>> getHeaderRows();

    List<List<Object>> getItemRows();

    List<List<Object>> getFooterRows();
}

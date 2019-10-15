package io.rocketbase.commons.email.template;

import io.rocketbase.commons.email.EmailTemplateBuilder;
import io.rocketbase.commons.email.TableConfig;

public interface RowLine {

    RowLineType getType();

    TableConfig nextRow();

    EmailTemplateBuilder.EmailTemplateConfigBuilder and();

    TableConfig alignment(Alignment alignment);


    enum RowLineType {
        ITEM, TOTAL;
    }
}

package io.rocketbase.commons.email.template;

import io.rocketbase.commons.email.EmailTemplateBuilder;
import io.rocketbase.commons.email.TableConfig;

import java.text.DecimalFormat;

public interface RowLine {

    RowLineType getType();

    TableConfig nextRow();

    EmailTemplateBuilder.EmailTemplateConfigBuilder and();

    void formatPrice(DecimalFormat decimalFormat);

    enum RowLineType {
        ITEM, TOTAL;
    }
}

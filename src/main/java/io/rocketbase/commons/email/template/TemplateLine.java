package io.rocketbase.commons.email.template;

import io.rocketbase.commons.email.EmailTemplateBuilder.EmailTemplateConfigBuilder;
import io.rocketbase.commons.email.model.HtmlTextEmail;

public interface TemplateLine {

    TemplateLineType getType();

    EmailTemplateConfigBuilder and();

    HtmlTextEmail build();

    enum TemplateLineType {

        TEXT, BUTTON, IMAGE, FOOTER;
    }


}

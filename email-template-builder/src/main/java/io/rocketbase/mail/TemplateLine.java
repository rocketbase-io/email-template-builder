package io.rocketbase.mail;

import io.rocketbase.mail.EmailTemplateBuilder.EmailTemplateConfigBuilder;
import io.rocketbase.mail.model.HtmlTextEmail;

public interface TemplateLine {

    TemplateLineType getType();

    EmailTemplateConfigBuilder and();

    HtmlTextEmail build();

    enum TemplateLineType {

        HR, TEXT, HTML, MARKDOWN, BUTTON, IMAGE, SIDE_IMAGE, ATTRIBUTE, TABLE, COPYRIGHT;
    }

}

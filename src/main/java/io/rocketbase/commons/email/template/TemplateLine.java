package io.rocketbase.commons.email.template;

public interface TemplateLine {

    TemplateLineType getType();

    enum TemplateLineType {

        TEXT, BUTTON, FOOTER;
    }


}

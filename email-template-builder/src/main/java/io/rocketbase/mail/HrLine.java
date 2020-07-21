package io.rocketbase.mail;

import io.rocketbase.mail.EmailTemplateBuilder.EmailTemplateConfigBuilder;
import io.rocketbase.mail.model.HtmlTextEmail;
import lombok.AccessLevel;
import lombok.Getter;

@Getter
public class HrLine implements TemplateLine {

    String margin;

    @Getter(AccessLevel.PRIVATE)
    EmailTemplateConfigBuilder builder;

    HrLine(EmailTemplateConfigBuilder builder) {
        this.builder = builder;
    }

    public HrLine margin(String margin) {
        this.margin = margin;
        return this;
    }

    @Override
    public TemplateLineType getType() {
        return TemplateLineType.HR;
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

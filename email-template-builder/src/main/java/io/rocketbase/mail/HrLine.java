package io.rocketbase.mail;

import io.rocketbase.mail.EmailTemplateBuilder.EmailTemplateConfigBuilder;
import io.rocketbase.mail.model.HtmlTextEmail;
import lombok.AccessLevel;
import lombok.Getter;

@Getter
public class HrLine implements TemplateLine {


    @Getter(AccessLevel.PRIVATE)
    EmailTemplateConfigBuilder builder;

    HrLine(EmailTemplateConfigBuilder builder) {
        this.builder = builder;
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

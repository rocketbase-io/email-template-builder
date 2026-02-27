package io.rocketbase.mail;

import io.rocketbase.mail.EmailTemplateBuilder.EmailTemplateConfigBuilder;
import io.rocketbase.mail.line.AbstractSpaceLine;
import io.rocketbase.mail.model.HtmlTextEmail;
import lombok.AccessLevel;
import lombok.Getter;

@Getter
public class SpaceLine extends AbstractSpaceLine<SpaceLine> implements TemplateLine {

    @Getter(AccessLevel.PRIVATE)
    EmailTemplateConfigBuilder builder;

    SpaceLine(EmailTemplateConfigBuilder builder) {
        super();
        this.builder = builder;
    }

    @Override
    public TemplateLineType getType() {
        return TemplateLineType.SPACE;
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

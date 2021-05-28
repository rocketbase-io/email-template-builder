package io.rocketbase.mail;

import io.rocketbase.mail.line.AbstractButtonLine;
import io.rocketbase.mail.model.HtmlTextEmail;
import lombok.AccessLevel;
import lombok.Getter;

@Getter
public class ButtonLine extends AbstractButtonLine<ButtonLine> implements TemplateLine {

    @Getter(AccessLevel.PRIVATE)
    EmailTemplateBuilder.EmailTemplateConfigBuilder builder;

    ButtonLine(EmailTemplateBuilder.EmailTemplateConfigBuilder builder, String text, String url) {
        super(text, url);
        this.builder = builder;
    }

    @Override
    public TemplateLineType getType() {
        return TemplateLineType.BUTTON;
    }

    @Override
    public EmailTemplateBuilder.EmailTemplateConfigBuilder and() {
        return builder;
    }

    @Override
    public HtmlTextEmail build() {
        return builder.build();
    }
}

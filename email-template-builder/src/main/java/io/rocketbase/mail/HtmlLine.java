package io.rocketbase.mail;

import io.rocketbase.mail.EmailTemplateBuilder.EmailTemplateConfigBuilder;
import io.rocketbase.mail.model.HtmlTextEmail;
import lombok.AccessLevel;
import lombok.Getter;

@Getter
public class HtmlLine implements TemplateLine {

    final String html;
    final String text;


    @Getter(AccessLevel.PRIVATE)
    EmailTemplateConfigBuilder builder;

    HtmlLine(EmailTemplateConfigBuilder builder, String html, String text) {
        this.builder = builder;
        this.html = html;
        this.text = text;
    }

    @Override
    public TemplateLineType getType() {
        return TemplateLineType.HTML;
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

package io.rocketbase.mail;

import io.rocketbase.mail.model.HtmlTextEmail;
import lombok.AccessLevel;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class CopyrightLine implements TemplateLine {

    final String name;
    Integer year;
    String url;

    @Getter(AccessLevel.PRIVATE)
    EmailTemplateBuilder.EmailTemplateConfigBuilder builder;

    CopyrightLine(EmailTemplateBuilder.EmailTemplateConfigBuilder builder, String name) {
        this.builder = builder;
        this.year = LocalDate.now().getYear();
        this.name = name;
    }

    public CopyrightLine year(Integer year) {
        this.year = year;
        return this;
    }

    public CopyrightLine url(String url) {
        this.url = url;
        return this;
    }

    @Override
    public TemplateLineType getType() {
        return TemplateLineType.COPYRIGHT;
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

package io.rocketbase.commons.email;

import io.rocketbase.commons.email.EmailTemplateBuilder.EmailTemplateConfigBuilder;
import io.rocketbase.commons.email.model.HtmlTextEmail;
import lombok.AccessLevel;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class CopyrightConfig {

    final Integer year;
    final String name;
    String url;

    @Getter(AccessLevel.PRIVATE)
    EmailTemplateConfigBuilder builder;

    CopyrightConfig(EmailTemplateConfigBuilder builder, String name) {
        this.builder = builder;
        this.year = LocalDate.now().getYear();
        this.name = name;
    }

    public CopyrightConfig(String name, int year) {
        this.year = year;
        this.name = name;
    }

    public CopyrightConfig url(String url) {
        this.url = url;
        return this;
    }

    public EmailTemplateConfigBuilder and() {
        return builder;
    }

    public HtmlTextEmail build() {
        return builder.build();
    }
}

package io.rocketbase.mail;

import io.rocketbase.mail.EmailTemplateBuilder.EmailTemplateConfigBuilder;
import io.rocketbase.mail.model.HtmlTextEmail;
import lombok.AccessLevel;
import lombok.Getter;

import java.util.LinkedHashMap;
import java.util.Map;

@Getter
public class AttributeLine implements TemplateLine {

    Map<String, String> map = new LinkedHashMap<>();

    @Getter(AccessLevel.PRIVATE)
    EmailTemplateConfigBuilder builder;

    AttributeLine(EmailTemplateConfigBuilder builder) {
        this.builder = builder;
    }

    public AttributeLine map(Map<String, String> map) {
        map.putAll(map);
        return this;
    }

    public AttributeLine keyValue(String key, String value) {
        map.put(key, value);
        return this;
    }

    @Override
    public TemplateLineType getType() {
        return TemplateLineType.ATTRIBUTE;
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

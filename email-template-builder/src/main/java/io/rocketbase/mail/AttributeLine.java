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
    String keyWidth;
    String valueWidth;

    @Getter(AccessLevel.PRIVATE)
    EmailTemplateConfigBuilder builder;

    AttributeLine(EmailTemplateConfigBuilder builder) {
        this.builder = builder;
    }

    public AttributeLine map(Map<String, String> map) {
        this.map.putAll(map);
        return this;
    }

    public AttributeLine keyValue(String key, String value) {
        map.put(key, value);
        return this;
    }

    /**
     * width in pixel
     */
    public AttributeLine keyWidth(int keyWidth) {
        this.keyWidth = String.valueOf(keyWidth);
        return this;
    }

    /**
     * width in percentage please add %
     */
    public AttributeLine keyWidth(String keyWidth) {
        this.keyWidth = keyWidth;
        return this;
    }

    /**
     * width in pixel
     */
    public AttributeLine valueWidth(int valueWidth) {
        this.valueWidth = String.valueOf(valueWidth);
        return this;
    }

    /**
     * width in percentage please add %
     */
    public AttributeLine valueWidth(String valueWidth) {
        this.valueWidth = valueWidth;
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

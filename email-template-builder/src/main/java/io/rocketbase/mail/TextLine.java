package io.rocketbase.mail;

import io.rocketbase.mail.EmailTemplateBuilder.EmailTemplateConfigBuilder;
import io.rocketbase.mail.line.AbstractTextLine;
import io.rocketbase.mail.model.HtmlTextEmail;
import io.rocketbase.mail.styling.FontWeight;
import lombok.AccessLevel;
import lombok.Getter;

@Getter
public class TextLine extends AbstractTextLine<TextLine> implements TemplateLine {


    @Getter(AccessLevel.PRIVATE)
    EmailTemplateBuilder.EmailTemplateConfigBuilder builder;

    TextLine(EmailTemplateBuilder.EmailTemplateConfigBuilder builder, String text) {
        super(text);
        this.builder = builder;
    }

    public TextLine h1() {
        tbFont(builder.getConfiguration().getFont().getH1());
        this.fontWeight = FontWeight.BOLD;
        return this;
    }

    public TextLine h2() {
        tbFont(builder.getConfiguration().getFont().getH2());
        this.fontWeight = FontWeight.BOLD;
        return this;
    }

    public TextLine h3() {
        tbFont(builder.getConfiguration().getFont().getH3());
        this.fontWeight = FontWeight.BOLD;
        return this;
    }

    public TextLine sub() {
        tbFont(builder.getConfiguration().getFont().getSub());
        return this;
    }

    @Override
    public TemplateLineType getType() {
        return TemplateLineType.TEXT;
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

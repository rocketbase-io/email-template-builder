package io.rocketbase.mail;

import io.rocketbase.mail.EmailTemplateBuilder.EmailTemplateConfigBuilder;
import io.rocketbase.mail.line.AbstractHtmlLine;
import io.rocketbase.mail.model.HtmlTextEmail;
import lombok.AccessLevel;
import lombok.Getter;

@Getter
public class HtmlLine extends AbstractHtmlLine<HtmlLine> implements TemplateLine {


    @Getter(AccessLevel.PRIVATE)
    EmailTemplateConfigBuilder builder;

    HtmlLine(EmailTemplateConfigBuilder builder, String html, String text) {
        super(html, text);
        this.builder = builder;
    }

    /**
     * @param html will extract text version out of it.<br>
     *             new line for br and p and links will get shifted to text -> link<br>
     *             all other tags will get removed
     */
    HtmlLine(EmailTemplateConfigBuilder builder, String html) {
        super(html);
        this.builder = builder;
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

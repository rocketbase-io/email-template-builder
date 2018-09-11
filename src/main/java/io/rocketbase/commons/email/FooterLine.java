package io.rocketbase.commons.email;

import io.rocketbase.commons.email.EmailTemplateBuilder.EmailTemplateConfigBuilder;
import io.rocketbase.commons.email.model.HtmlTextEmail;
import io.rocketbase.commons.email.template.Alignment;
import io.rocketbase.commons.email.template.TemplateLine;
import lombok.AccessLevel;
import lombok.Getter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.google.common.html.HtmlEscapers;

@Getter
public class FooterLine implements TemplateLine {

    String text;
    String escapedText;
    boolean asHtml;
    Alignment alignment = Alignment.CENTER;

    @Getter(AccessLevel.PRIVATE)
    EmailTemplateConfigBuilder builder;

    FooterLine(EmailTemplateConfigBuilder builder, String text) {
        this.builder = builder;

        Document doc = Jsoup.parse(text);
        this.asHtml = doc.body().children().size() > 0;
        handleTextAsHtml(doc);
    }
    
    FooterLine(EmailTemplateConfigBuilder builder, String text, boolean asHtml) {
        this.builder = builder;

        this.asHtml = asHtml;
        if (asHtml) {
            Document doc = Jsoup.parse(text);
            handleTextAsHtml(doc);
        } else {
            this.text = HtmlEscapers.htmlEscaper().escape(text);
            
            this.escapedText = text;
        }
    }

    private void handleTextAsHtml(Document doc) {
        Elements links = doc.body().getElementsByTag("a");
        links.forEach(e -> {
            if (e.attr("style").equals("")) {
                // add correct styling for links
                e.attr("style", "font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 12px; color: #999; text-decoration: underline; margin: 0;");
            }
        });
        this.text = doc.body().html();

        this.escapedText = extractHtml(doc);
    }

    public FooterLine alignment(Alignment alignment) {
        this.alignment = alignment;
        return this;
    }

    public FooterLine left() {
        this.alignment = Alignment.LEFT;
        return this;
    }

    public FooterLine center() {
        this.alignment = Alignment.CENTER;
        return this;
    }

    public FooterLine right() {
        this.alignment = Alignment.RIGHT;
        return this;
    }

    @Override
    public TemplateLineType getType() {
        return TemplateLineType.FOOTER;
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

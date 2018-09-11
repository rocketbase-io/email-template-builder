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
public class TextLine implements TemplateLine {

    String text;
    String escapedText;
    boolean asHtml;
    Alignment alignment = Alignment.LEFT;

    @Getter(AccessLevel.PRIVATE)
    EmailTemplateConfigBuilder builder;

    TextLine(EmailTemplateConfigBuilder builder, String text) {
        this.builder = builder;

        Document doc = Jsoup.parse(text);
        this.asHtml = doc.body().children().size() > 0;
        handleTextAsHtml(doc);
    }

    TextLine(EmailTemplateConfigBuilder builder, String text, boolean asHtml) {
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
                e.attr("style", "font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; color: #348eda; text-decoration: underline; margin: 0;");
            }
        });
        this.text = doc.body().html();

        this.escapedText = extractHtml(doc);
    }

    public TextLine alignment(Alignment alignment) {
        this.alignment = alignment;
        return this;
    }

    public TextLine left() {
        this.alignment = Alignment.LEFT;
        return this;
    }

    public TextLine center() {
        this.alignment = Alignment.CENTER;
        return this;
    }

    public TextLine right() {
        this.alignment = Alignment.RIGHT;
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

package io.rocketbase.commons.email;

import io.rocketbase.commons.email.EmailTemplateBuilder.EmailTemplateConfigBuilder;
import io.rocketbase.commons.email.model.HtmlTextEmail;
import io.rocketbase.commons.email.template.Alignment;
import io.rocketbase.commons.email.template.ColorPalette;
import io.rocketbase.commons.email.template.TemplateLine;
import io.rocketbase.commons.email.template.styling.FontStyle;
import io.rocketbase.commons.email.template.styling.FontWeight;
import io.rocketbase.commons.email.template.styling.TextDecoration;
import io.rocketbase.commons.email.util.HtmlEscaper;
import lombok.AccessLevel;
import lombok.Getter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
public class TextLine implements TemplateLine {

    @Getter
    final TemplateLineType type;
    String text;
    String escapedText;
    boolean asHtml;
    Alignment alignment = Alignment.LEFT;
    FontStyle fontStyle = FontStyle.NORMAL;
    FontWeight fontWeight = FontWeight.NORMAL;
    List<TextDecoration> textDecorations = new ArrayList<>();
    String color;
    String textCssStyle;

    @Getter(AccessLevel.PRIVATE)
    EmailTemplateBuilder.EmailTemplateConfigBuilder builder;

    TextLine(TemplateLineType type, EmailTemplateBuilder.EmailTemplateConfigBuilder builder, String text) {
        this.type = type;
        this.builder = builder;

        Document doc = Jsoup.parse(text);
        this.asHtml = doc.body().children().size() > 0;
        handleTextAsHtml(doc);
    }

    TextLine(TemplateLineType type, EmailTemplateBuilder.EmailTemplateConfigBuilder builder, String text, boolean asHtml) {
        this.type = type;
        this.builder = builder;

        this.asHtml = asHtml;
        if (asHtml) {
            Document doc = Jsoup.parse(text);
            handleTextAsHtml(doc);
        } else {
            this.text = HtmlEscaper.escapeHtml(text);

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

    public TextLine fontStyle(FontStyle fontStyle) {
        this.fontStyle = fontStyle;
        return this;
    }

    public TextLine italic() {
        this.fontStyle = FontStyle.ITALIC;
        return this;
    }

    public TextLine oblique() {
        this.fontStyle = FontStyle.OBLIQUE;
        return this;
    }

    public TextLine fontWeight(FontWeight fontWeight) {
        this.fontWeight = fontWeight;
        return this;
    }

    public TextLine bold() {
        this.fontWeight = FontWeight.BOLD;
        return this;
    }

    public TextLine lighter() {
        this.fontWeight = FontWeight.LIGHTER;
        return this;
    }

    public TextLine textDecoration(TextDecoration... textDecoration) {
        this.textDecorations = Arrays.asList(textDecoration);
        return this;
    }

    public TextLine underline() {
        this.textDecorations = Arrays.asList(TextDecoration.UNDERLINE);
        return this;
    }

    /**
     * value similar to css value for example: <br>
     * red, blue<br>
     * #000, #808080, ...
     */
    public TextLine color(String color) {
        this.color = color;
        return this;
    }

    public TextLine color(ColorPalette color) {
        this.color = "#" + color.getHexCode();
        return this;
    }

    void generateTextCssStyle() {
        StringBuffer stringBuffer = new StringBuffer();
        if (fontStyle != null) {
            stringBuffer.append("font-style: ").append(fontStyle.getValue()).append(";");
        }
        if (fontWeight != null) {
            stringBuffer.append("font-weight: ").append(fontWeight.getValue()).append(";");
        }
        if (textDecorations != null && !textDecorations.isEmpty()) {
            stringBuffer.append("text-decoration:");
            for (TextDecoration t : textDecorations) {
                stringBuffer.append(" ").append(t.getValue());
            }
            stringBuffer.append(";");
        }
        if (color != null && !color.isEmpty()) {
            stringBuffer.append("color: ").append(color).append(";");
        }
        this.textCssStyle = stringBuffer.toString();
    }

    @Override
    public EmailTemplateConfigBuilder and() {
        generateTextCssStyle();
        return builder;
    }

    @Override
    public HtmlTextEmail build() {
        generateTextCssStyle();
        return builder.build();
    }
}

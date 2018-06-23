package io.rocketbase.commons.email;

import com.mitchellbosecke.pebble.PebbleEngine;
import com.mitchellbosecke.pebble.error.PebbleException;
import com.mitchellbosecke.pebble.template.PebbleTemplate;
import io.rocketbase.commons.email.model.HtmlTextEmail;
import io.rocketbase.commons.email.template.*;
import io.rocketbase.commons.email.template.TemplateLine.TemplateLineType;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.StringWriter;
import java.io.Writer;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public final class EmailTemplateBuilder {

    private static final PebbleEngine ENGINE = new PebbleEngine.Builder().build();

    private List<TemplateLine> templateLines;
    private HeaderConfig headerConfig;
    private CopyrightConfig copyrightConfig;

    private EmailTemplateBuilder() {
        templateLines = new ArrayList<>();
    }

    public static EmailTemplateBuilder builder() {
        return new EmailTemplateBuilder();
    }

    public EmailTemplateBuilder header(String title) {
        return header(title, null);
    }

    public EmailTemplateBuilder header(String title, ColorStyle style) {
        headerConfig = new HeaderConfig(title, style != null ? style : ColorStyle.BASE_STYLE);
        return this;
    }

    public EmailTemplateBuilder addText(String text) {
        templateLines.add(new TextLine(text, false));
        return this;
    }

    public EmailTemplateBuilder addHtml(String html) {
        templateLines.add(new TextLine(html, true));
        return this;
    }

    public EmailTemplateBuilder addButton(String text, String url) {
        return addButton(text, url, null);
    }

    public EmailTemplateBuilder addButton(String text, String url, ColorStyle style) {
        templateLines.add(new ButtonLine(text, url, style != null ? style : ColorStyle.BASE_STYLE));
        return this;
    }

    public EmailTemplateBuilder addFooter(String text, boolean asHtml) {
        templateLines.add(new FooterLine(text, asHtml));
        return this;
    }

    public EmailTemplateBuilder copyright(String name, String url) {
        return copyright(name, url, LocalDate.now().getYear());
    }

    public EmailTemplateBuilder copyright(String name, String url, Integer year) {
        copyrightConfig = new CopyrightConfig(name, url, year);
        return this;
    }

    @SneakyThrows
    public HtmlTextEmail build() throws PebbleException {
        PebbleTemplate htmlTemplate = ENGINE.getTemplate("templates/email/base.html");
        PebbleTemplate textTemplate = ENGINE.getTemplate("templates/email/base.txt");

        Map<String, Object> template = new HashMap<>();
        template.put("header", headerConfig);
        template.put("lines", templateLines.stream()
                .filter(l -> !l.getType().equals(TemplateLineType.FOOTER))
                .collect(Collectors.toList()));
        template.put("footers", templateLines.stream()
                .filter(l -> l.getType().equals(TemplateLineType.FOOTER))
                .collect(Collectors.toList()));
        template.put("copyright", copyrightConfig);


        Writer htmlWriter = new StringWriter();
        htmlTemplate.evaluate(htmlWriter, template);

        Writer textWrite = new StringWriter();
        textTemplate.evaluate(textWrite, template);

        return new HtmlTextEmail(htmlWriter.toString(), textWrite.toString());
    }
}

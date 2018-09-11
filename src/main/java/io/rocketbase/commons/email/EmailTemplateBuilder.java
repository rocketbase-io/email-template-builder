package io.rocketbase.commons.email;

import com.mitchellbosecke.pebble.PebbleEngine;
import com.mitchellbosecke.pebble.error.PebbleException;
import com.mitchellbosecke.pebble.template.PebbleTemplate;
import io.rocketbase.commons.email.model.HtmlTextEmail;
import io.rocketbase.commons.email.template.TemplateLine;
import io.rocketbase.commons.email.template.TemplateLine.TemplateLineType;
import lombok.SneakyThrows;

import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class EmailTemplateBuilder {

    private static final PebbleEngine ENGINE = new PebbleEngine.Builder().build();

    public static EmailTemplateConfigBuilder builder() {
        return new EmailTemplateConfigBuilder();
    }

    @SneakyThrows
    static HtmlTextEmail build(List<TemplateLine> templateLines, ImageLine logo, HeaderConfig headerConfig, CopyrightConfig copyrightConfig) throws PebbleException {
        PebbleTemplate htmlTemplate = ENGINE.getTemplate("templates/email/base.html");
        PebbleTemplate textTemplate = ENGINE.getTemplate("templates/email/base.txt");

        Map<String, Object> template = new HashMap<>();
        template.put("logo", logo);
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

    public static class EmailTemplateConfigBuilder {

        private List<TemplateLine> templateLines = new ArrayList<>();
        private ImageLine logo;
        private HeaderConfig headerConfig;
        private CopyrightConfig copyrightConfig;

        public ImageLine logo(String src, String alt, int width, int height) {
            logo = new ImageLine(this, src, alt, width, height);
            return logo;
        }

        public HeaderConfig header(String title) {
            headerConfig = new HeaderConfig(this, title);
            return headerConfig;
        }

        /**
         * @param textOrHtml detects whether text or html <br>
         *                   sometime the detection is not working as expected. then you can use addPlainText or addHtml to overrule the detection
         */
        public TextLine addText(String textOrHtml) {
            TextLine line = new TextLine(this, textOrHtml);
            templateLines.add(line);
            return line;
        }

        private TextLine addText(String textOrHtml, boolean asHtml) {
            TextLine line = new TextLine(this, textOrHtml, asHtml);
            templateLines.add(line);
            return line;
        }

        /**
         * @param html HTML formatted text
         */
        public TextLine addHtml(String html) {
            return addText(html, true);
        }

        /**
         * @param text text that shall be shown exactly as given - e.g. it will be encoded when it shall be shown as HTML
         */
        public TextLine addPlainText(String text) {
            return addText(text, false);
        }

        public ButtonLine addButton(String text, String url) {
            ButtonLine line = new ButtonLine(this, text, url);
            templateLines.add(line);
            return line;
        }

        public ImageLine addImage(String src, String alt, int width, int height) {
            ImageLine line = new ImageLine(this, src, alt, width, height);
            templateLines.add(line);
            return line;
        }

        /**
         * @param textOrHtml detects whether text or html <br>
         *                   sometime the detection is not working as expected. then you can use addPlainText or addHtml to overrule the detection
         */
        public FooterLine addFooter(String textOrHtml) {
            FooterLine line = new FooterLine(this, textOrHtml);
            templateLines.add(line);
            return line;
        }

        private FooterLine addFooter(String textOrHtml, boolean asHtml) {
            FooterLine line = new FooterLine(this, textOrHtml, asHtml);
            templateLines.add(line);
            return line;
        }

        /**
         * @param html HTML formatted text
         */
        public FooterLine addHtmlFooter(String html) {
            return addFooter(html, true);
        }

        /**
         * @param text text that shall be shown exactly as given - e.g. it will be encoded when it shall be shown as HTML
         */
        public FooterLine addPlainTextFooter(String text) {
            return addFooter(text, true);
        }

        public CopyrightConfig copyright(String name) {
            copyrightConfig = new CopyrightConfig(this, name);
            return copyrightConfig;
        }

        public HtmlTextEmail build() {
            return EmailTemplateBuilder.build(templateLines, logo, headerConfig, copyrightConfig);
        }

    }
}

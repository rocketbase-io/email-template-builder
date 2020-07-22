package io.rocketbase.mail;

import com.mitchellbosecke.pebble.PebbleEngine;
import com.mitchellbosecke.pebble.error.PebbleException;
import com.mitchellbosecke.pebble.template.PebbleTemplate;
import io.rocketbase.mail.config.TbConfiguration;
import io.rocketbase.mail.model.HtmlTextEmail;
import io.rocketbase.mail.preset.TableFourColumnPreset;
import io.rocketbase.mail.preset.TableSimplePreset;
import io.rocketbase.mail.preset.TableSimpleWithImagePreset;
import lombok.Getter;
import lombok.SneakyThrows;

import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class EmailTemplateBuilder {

    private static final PebbleEngine ENGINE = new PebbleEngine.Builder()
            .strictVariables(false)
            .autoEscaping(false)
            .build();

    public static EmailTemplateConfigBuilder builder() {
        return new EmailTemplateConfigBuilder();
    }


    @SneakyThrows
    static HtmlTextEmail build(TbConfiguration configuration, Header header, List<TemplateLine> contentLines, List<TemplateLine> footerLines) throws PebbleException {
        PebbleTemplate htmlTemplate = ENGINE.getTemplate("templates/email/layout.html");
        PebbleTemplate textTemplate = ENGINE.getTemplate("templates/email/layout.txt");


        Map<String, Object> template = new HashMap<>();
        template.put("c", configuration);
        template.put("header", header);
        template.put("contentLines", contentLines);
        template.put("footerLines", footerLines);


        Writer htmlWriter = new StringWriter();
        htmlTemplate.evaluate(htmlWriter, template);

        Writer textWrite = new StringWriter();
        textTemplate.evaluate(textWrite, template);

        return new HtmlTextEmail(htmlWriter.toString(), textWrite.toString());
    }

    public static class EmailTemplateConfigBuilder {

        @Getter
        private TbConfiguration configuration = TbConfiguration.newInstance();
        private Header header;
        private List<TemplateLine> contentLines = new ArrayList<>();
        private List<TemplateLine> footerLines = new ArrayList<>();

        public EmailTemplateConfigBuilder configuration(TbConfiguration configuration) {
            this.configuration = configuration;
            return this;
        }

        /**
         * add's a generic content line
         */
        public EmailTemplateConfigBuilder addContent(TemplateLine line) {
            contentLines.add(line);
            return this;
        }

        /**
         * add's a generic footer line
         */
        public EmailTemplateConfigBuilder addFooter(TemplateLine line) {
            footerLines.add(line);
            return this;
        }

        public Header header() {
            header = new Header(this);
            return header;
        }

        public HrLine hr() {
            HrLine line = new HrLine(this);
            contentLines.add(line);
            return line;
        }

        public TextLine text(String text) {
            TextLine line = new TextLine(this, text);
            contentLines.add(line);
            return line;
        }

        public HtmlLine html(String html, String text) {
            HtmlLine line = new HtmlLine(this, html, text);
            contentLines.add(line);
            return line;
        }

        public ImageLine image(String src) {
            ImageLine line = new ImageLine(this, src);
            contentLines.add(line);
            return line;
        }

        public ButtonLine button(String text, String url) {
            ButtonLine line = new ButtonLine(this, text, url);
            contentLines.add(line);
            return line;
        }

        public AttributeLine attribute() {
            AttributeLine line = new AttributeLine(this);
            contentLines.add(line);
            return line;
        }

        public EmailTemplateConfigBuilder table(TableLine tableLine) {
            contentLines.add(tableLine);
            return this;
        }

        public TableSimplePreset tableSimple(String numberFormat) {
            TableSimplePreset line = new TableSimplePreset(this, numberFormat);
            contentLines.add(line);
            return line;
        }

        public TableSimpleWithImagePreset tableSimpleWithImage(String numberFormat) {
            TableSimpleWithImagePreset line = new TableSimpleWithImagePreset(this, numberFormat);
            contentLines.add(line);
            return line;
        }

        public TableFourColumnPreset tableFourColumn(String taxFormat, String amountFormat) {
            TableFourColumnPreset line = new TableFourColumnPreset(this, taxFormat, amountFormat);
            contentLines.add(line);
            return line;
        }

        public CopyrightLine copyright(String name) {
            CopyrightLine line = new CopyrightLine(this, name);
            footerLines.add(line);
            return line;
        }

        public HrLine footerHr() {
            HrLine line = new HrLine(this);
            footerLines.add(line);
            return line;
        }

        public TextLine footerText(String text) {
            TextLine line = new TextLine(this, text);
            footerLines.add(line);
            return line;
        }

        public HtmlLine footerHtml(String html, String text) {
            HtmlLine line = new HtmlLine(this, html, text);
            footerLines.add(line);
            return line;
        }

        public ImageLine footerImage(String src) {
            ImageLine line = new ImageLine(this, src);
            footerLines.add(line);
            return line;
        }

        public HtmlTextEmail build() {
            return EmailTemplateBuilder.build(configuration, header, contentLines, footerLines);
        }

    }
}

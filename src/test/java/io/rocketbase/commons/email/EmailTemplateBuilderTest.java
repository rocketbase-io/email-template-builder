package io.rocketbase.commons.email;

import io.rocketbase.commons.email.EmailTemplateBuilder.EmailTemplateConfigBuilder;
import io.rocketbase.commons.email.model.HtmlTextEmail;
import io.rocketbase.commons.email.template.Alignment;
import io.rocketbase.commons.email.template.ColorStyle;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class EmailTemplateBuilderTest {

    @Test
    public void standardTestHtml() {
        // given
        EmailTemplateConfigBuilder builder = EmailTemplateBuilder.builder();
        String header = "test";
        ColorStyle headerStyle = new ColorStyle("000000", "ff0000");
        // when
        HtmlTextEmail htmlTextEmail = builder.header(header).color(headerStyle).and()
                .addText("sample-text").and()
                .addButton("button 1", "http://adasd").and()
                .copyright("rocketbase").url("https://www.rocketbase.io")
                .build();
        // then
        assertThat(htmlTextEmail, notNullValue());
        Document document = Jsoup.parse(htmlTextEmail.getHtml());
        Element headerElement = document.getElementsByClass("alert-warning").get(0);
        String style = headerElement.attr("style");
        String text = headerElement.childNode(0).outerHtml();
        assertThat(headerElement, notNullValue());
        assertThat(style, containsString("background-color: #" + headerStyle.getBg()));
        assertThat(style, containsString("color: #" + headerStyle.getText()));
        assertThat(text, containsString(header));
    }

    @Test
    public void standardTableTestHtml() {
        // given
        EmailTemplateConfigBuilder builder = EmailTemplateBuilder.builder();

        String header = "test";
        ColorStyle headerStyle = new ColorStyle("000000", "ff0000");
        // when
        HtmlTextEmail htmlTextEmail = builder
                .header(header).color(headerStyle).and()
                .addText("sample-text").and()

                .addTable()
                .addHeader("Invoice #12345<br>June 01 2014", true, Alignment.LEFT)
                .addItemRow("Special Product", "123,00 €").nextRow()
                .addItemRow("Short service", BigDecimal.valueOf(103, 1)).nextRow()
                .addTotalRow(BigDecimal.valueOf(13333, 2)).totalCaption("Gesamt")

                .and()
                .addButton("button 1", "http://adasd").and()
                .copyright("rocketbase").url("https://www.rocketbase.io")
                .build();
        // then
        assertThat(htmlTextEmail, notNullValue());
        Document document = Jsoup.parse(htmlTextEmail.getHtml());
        Element headerElement = document.getElementsByClass("alert-warning").get(0);
        String style = headerElement.attr("style");
        String text = headerElement.childNode(0).outerHtml();
        assertThat(headerElement, notNullValue());
        assertThat(style, containsString("background-color: #" + headerStyle.getBg()));
        assertThat(style, containsString("color: #" + headerStyle.getText()));
        assertThat(text, containsString(header));
    }

    @Test
    public void withoutHeaderAndFooterHtml() {
        // given
        EmailTemplateConfigBuilder builder = EmailTemplateBuilder.builder();

        String firstText = "sample-text 1";
        String secondText = "sample-text 2";

        String button1Text = "button 1";
        String button1Url = "http://url1?test=2134&amp;bla=blub";

        String button2Text = "button 2";
        ColorStyle secondButtonStyle = new ColorStyle("000000", "00ff00");

        // when
        HtmlTextEmail htmlTextEmail = builder
                .addText(firstText).and()
                .addButton(button1Text, button1Url).and()
                .addText(secondText).and()
                .addButton(button2Text, "http://url2").color(secondButtonStyle)
                .build();
        // then
        assertThat(htmlTextEmail, notNullValue());
        Document document = Jsoup.parse(htmlTextEmail.getHtml());
        Elements contentBlocks = document.getElementsByClass("content-block");

        assertThat(contentBlocks.size(), equalTo(4));
        assertThat(contentBlocks.get(0).childNode(0).outerHtml(), containsString(firstText));

        String button1Html = contentBlocks.get(1).html();
        assertThat(button1Html, containsString("background-color: #" + ColorStyle.BASE_STYLE.getBg()));
        assertThat(button1Html, containsString("color: #" + ColorStyle.BASE_STYLE.getText()));
        assertThat(button1Html, containsString(button1Text));
        assertThat(button1Html, containsString(button1Url));


        assertThat(contentBlocks.get(2).childNode(0).outerHtml(), containsString(secondText));

        String button2Html = contentBlocks.get(3).html();
        assertThat(button2Html, containsString("background-color: #" + secondButtonStyle.getBg()));
        assertThat(button2Html, containsString("color: #" + secondButtonStyle.getText()));
        assertThat(button2Html, containsString(button2Text));

        Elements footerElements = document.getElementsByClass("footer");
        assertThat(footerElements.size(), equalTo(0));
    }


    @Test
    public void standardTestText() {
        // given
        EmailTemplateConfigBuilder builder = EmailTemplateBuilder.builder();
        String header = "test";
        String text = "sample-text";
        String buttonText = "button 1";
        String buttonUrl = "http://adasd";
        String copyrightName = "rocketbase";
        String copyrightUrl = "https://www.rocketbase.io";
        // when
        HtmlTextEmail htmlTextEmail = builder.header(header).and()
                .addText(text).and()
                .addButton(buttonText, buttonUrl).and()
                .copyright(copyrightName).url(copyrightUrl)
                .build();
        // then
        assertThat(htmlTextEmail, notNullValue());
        String lineBreak = System.getProperty("line.separator");
        assertThat(htmlTextEmail.getText(), equalTo(new StringBuffer()
                .append(header).append(lineBreak)
                .append(text).append(lineBreak)
                .append(buttonText).append(" -> ").append(buttonUrl).append(lineBreak)
                .append(LocalDate.now().getYear()).append(" ").append(copyrightName).append(" -> ").append(copyrightUrl)
                .toString()
        ));
    }

    @Test
    public void forcedText() {
        // given
        EmailTemplateConfigBuilder builder = EmailTemplateBuilder.builder();
        String header = "test";
        ColorStyle headerStyle = new ColorStyle("000000", "ff0000");
        // when
        HtmlTextEmail htmlTextEmail = builder.header(header).color(headerStyle).and()
                .addPlainText("sample <b>bold</b> text &Uuml;mlaut").and()
                .build();
        // then
        assertThat(htmlTextEmail, notNullValue());
        Document document = Jsoup.parse(htmlTextEmail.getHtml());
        Element headerElement = document.getElementsByClass("alert-warning").get(0);
        String style = headerElement.attr("style");
        String text = headerElement.childNode(0).outerHtml();
        assertThat(headerElement, notNullValue());
        assertThat(style, containsString("background-color: #" + headerStyle.getBg()));
        assertThat(style, containsString("color: #" + headerStyle.getText()));
        assertThat(text, containsString(header));
        assertThat(htmlTextEmail.getHtml(), containsString("sample &amp;lt;b&amp;gt;bold&amp;lt;/b&amp;gt; text &amp;amp;Uuml;mlaut"));
        assertThat(htmlTextEmail.getText(), containsString("sample <b>bold</b> text &Uuml;mlaut"));
    }

    @Test
    public void forcedHtml() {
        // given
        EmailTemplateConfigBuilder builder = EmailTemplateBuilder.builder();
        String header = "test";
        ColorStyle headerStyle = new ColorStyle("000000", "ff0000");
        // when
        HtmlTextEmail htmlTextEmail = builder.header(header).color(headerStyle).and()
                .addHtml("sample <b>bold</b> text &Uuml;mlaut &lt; 17").and()
                .build();
        // then
        assertThat(htmlTextEmail, notNullValue());
        Document document = Jsoup.parse(htmlTextEmail.getHtml());
        Element headerElement = document.getElementsByClass("alert-warning").get(0);
        String style = headerElement.attr("style");
        String text = headerElement.childNode(0).outerHtml();
        assertThat(headerElement, notNullValue());
        assertThat(style, containsString("background-color: #" + headerStyle.getBg()));
        assertThat(style, containsString("color: #" + headerStyle.getText()));
        assertThat(text, containsString(header));
        assertThat(htmlTextEmail.getHtml(), containsString("sample <b>bold</b> text Ümlaut &lt; 17"));
        assertThat(htmlTextEmail.getText(), containsString("sample bold text Ümlaut < 17"));
    }
}

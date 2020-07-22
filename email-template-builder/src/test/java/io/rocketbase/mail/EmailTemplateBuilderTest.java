package io.rocketbase.mail;

import io.rocketbase.mail.config.TbConfiguration;
import io.rocketbase.mail.model.HtmlTextEmail;
import io.rocketbase.mail.styling.ColorStyle;
import io.rocketbase.mail.styling.ColorStyleSimple;
import org.junit.Test;
import org.simplejavamail.api.email.Email;
import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class EmailTemplateBuilderTest {

    private Mailer mailer;

    protected Mailer getMailer() {
        if (mailer == null) {
            mailer = MailerBuilder.withSMTPServer("localhost", 1025)
                    .buildMailer();
        }
        return mailer;
    }

    protected void sentEmail(String subject, HtmlTextEmail content) {
        try {
            Email email = EmailBuilder.startingBlank()
                    .to("hello@test.de")
                    .from("test@localhost")
                    .withSubject(subject)
                    .withHTMLText(content.getHtml())
                    .withPlainText(content.getText())
                    .buildEmail();
            getMailer().sendMail(email);
        } catch (Exception e) {
            // ignore error here - works only on local machine for visible test-purpose
        }
    }

    @Test
    public void standardTestHtml() {
        // given
        EmailTemplateBuilder.EmailTemplateConfigBuilder builder = EmailTemplateBuilder.builder();
        String header = "test";
        // when
        TbConfiguration config = TbConfiguration.newInstance();
        config.getContent().setWidth(800);
        config.getContent().setFull(true);
        HtmlTextEmail htmlTextEmail = builder
                .configuration(config)
                .header()
                .logo("https://www.rocketbase.io/img/logo-dark.png").logoHeight(41)
                .and()
                .text("sample-text").and()
                .text("link to google").linkUrl("https://www.google").and()
                .text("link to rocketbase").bold().underline().linkUrl("https://www.rocketbase.io").color(ColorStyle.RED).center().and()
                .image("https://cdn.rocketbase.io/assets/loading/no-image.jpg").alt("no-picture").width(300).center().and()
                .hr().margin("20px 0").and()
                .image("https://cdn.rocketbase.io/assets/signature/rocketbase-logo-signature-2020.png").alt("rocketbase").width(150).linkUrl("https://www.rocketbase.io").right().and()
                .button("click me here", "http://localhost").red().right().and()
                .button("gray is the new pink", "http://localhost").gray().left().and()
                .button("button 1", "http://adasd").and()
                .text("sample text").and()
                .attribute()
                .keyValue("KEY 1", "Value 123")
                .keyValue("KEY 2", "Value ABC")
                .and()
                .text("another text").and()
                .copyright("rocketbase").url("https://www.rocketbase.io").and()
                .footerText("my agb can be found here").linkUrl("http://localhost").and()
                .footerImage("https://cdn.rocketbase.io/assets/loading/no-image.jpg").height(50).right().and()
                .footerHr().and()
                .footerText("my little text").underline().bold().and()
                .footerImage("https://cdn.rocketbase.io/assets/loading/no-image.jpg").width(100).left().linkUrl("https://www.rocketbase.io").and()
                .build();
        // then
        assertThat(htmlTextEmail, notNullValue());

        sentEmail("standardTestHtml", htmlTextEmail);
    }

    @Test
    public void standardTableTestHtml() {
        // given
        EmailTemplateBuilder.EmailTemplateConfigBuilder builder = EmailTemplateBuilder.builder();

        String header = "test";
        ColorStyleSimple headerStyle = new ColorStyleSimple("000000", "ff0000");
        // when
        HtmlTextEmail htmlTextEmail = builder
                .header().text(header).and()
                .text("sample-text").and()
                .tableSimple("#.## '€'")
                .headerRow("Description", "Amount")
                .itemRow("Special Product", BigDecimal.valueOf(1333, 2))
                .itemRow("Short service", BigDecimal.valueOf(103, 1))
                .footerRow("Total", BigDecimal.valueOf(2363, 2))
                .and()
                .button("button 1", "http://adasd").and()
                .copyright("rocketbase").url("https://www.rocketbase.io")
                .build();
        // then
        assertThat(htmlTextEmail, notNullValue());
        sentEmail("standardTableTestHtml", htmlTextEmail);
    }

    @Test
    public void withoutHeaderAndFooterHtml() {
        // given
        EmailTemplateBuilder.EmailTemplateConfigBuilder builder = EmailTemplateBuilder.builder();

        String firstText = "sample-text 1";
        String secondText = "sample-text 2";

        String button1Text = "button 1";
        String button1Url = "http://url1?test=2134&amp;bla=blub";

        String button2Text = "button 2";

        // when
        HtmlTextEmail htmlTextEmail = builder
                .text(firstText).and()
                .button(button1Text, button1Url).green().and()
                .text(secondText).and()
                .button(button2Text, "http://url2").red()
                .build();
        // then
        assertThat(htmlTextEmail, notNullValue());

        sentEmail("withoutHeaderAndFooterHtml", htmlTextEmail);
    }


    @Test
    public void standardTestText() {
        // given
        EmailTemplateBuilder.EmailTemplateConfigBuilder builder = EmailTemplateBuilder.builder();
        String header = "test";
        String text = "sample-text";
        String buttonText = "button 1";
        String buttonUrl = "http://adasd";
        String copyrightName = "rocketbase";
        String copyrightUrl = "https://www.rocketbase.io";
        // when
        HtmlTextEmail htmlTextEmail = builder.header().text(header).and()
                .text(text).and()
                .button(buttonText, buttonUrl).and()
                .copyright(copyrightName).url(copyrightUrl)
                .build();
        // then
        assertThat(htmlTextEmail, notNullValue());
        String lineBreak = System.getProperty("line.separator");
        assertThat(htmlTextEmail.getText(), equalTo(new StringBuffer()
                .append("***************************").append(lineBreak)
                .append(header).append(lineBreak)
                .append("***************************").append(lineBreak).append(lineBreak).append(lineBreak)
                .append(text).append(lineBreak).append(lineBreak)
                .append(buttonText).append(" -> ").append(buttonUrl).append(lineBreak).append(lineBreak)
                .append("-----------").append(lineBreak).append(lineBreak).append(lineBreak)
                .append("©").append(LocalDate.now().getYear()).append(" ").append(copyrightName).append(" -> ").append(copyrightUrl)
                .append(lineBreak)
                .toString()
        ));

        sentEmail("standardTestText", htmlTextEmail);
    }

    @Test
    public void forcedText() {
        // given
        EmailTemplateBuilder.EmailTemplateConfigBuilder builder = EmailTemplateBuilder.builder();
        // when
        HtmlTextEmail htmlTextEmail = builder
                .text("sample <b>bold</b> text &Uuml;mlaut").and()
                .build();
        // then
        assertThat(htmlTextEmail, notNullValue());
        assertThat(htmlTextEmail.getHtml(), containsString("sample &lt;b&gt;bold&lt;/b&gt; text &amp;Uuml;mlaut"));
        assertThat(htmlTextEmail.getText(), containsString("sample <b>bold</b> text &Uuml;mlaut"));

        sentEmail("forcedText", htmlTextEmail);
    }

    @Test
    public void forcedHtml() {
        // given
        EmailTemplateBuilder.EmailTemplateConfigBuilder builder = EmailTemplateBuilder.builder();
        // when
        HtmlTextEmail htmlTextEmail = builder
                .html("sample <b>bold</b> text &Uuml;mlaut &lt; 17", "sample bold text Ümlaut < 17").and()
                .build();
        // then
        assertThat(htmlTextEmail, notNullValue());
        assertThat(htmlTextEmail.getHtml(), containsString("sample <b>bold</b> text &Uuml;mlaut &lt; 17"));
        assertThat(htmlTextEmail.getText(), containsString("sample bold text Ümlaut < 17"));

        sentEmail("forcedHtml", htmlTextEmail);
    }
}

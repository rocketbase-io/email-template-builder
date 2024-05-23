package io.rocketbase.mail;

import io.rocketbase.mail.config.TbConfiguration;
import io.rocketbase.mail.model.HtmlTextEmail;
import io.rocketbase.mail.styling.Alignment;
import io.rocketbase.mail.styling.ColorStyle;
import io.rocketbase.mail.styling.ColorStyleSimple;
import io.rocketbase.mail.styling.VerticalAlignment;
import io.rocketbase.mail.table.*;
import lombok.AccessLevel;
import lombok.Getter;
import org.junit.jupiter.api.Test;
import org.simplejavamail.api.email.Email;
import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class EmailTemplateBuilderTest {

    private Mailer mailer;

    protected Mailer getMailer() {
        if (mailer == null) {
            // default
            mailer = MailerBuilder.withSMTPServer("localhost", 2525)
                    .buildMailer();
        }
        return mailer;
    }

    protected void sentEmail(String subject, HtmlTextEmail content) {
        try {
            Email email = EmailBuilder.startingBlank()
                    .to("melistik@icloud.com")
                    .from("service@rocketbase.io")
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
        HtmlTextEmail htmlTextEmail = builder
                .header()
                .logo("https://www.rocketbase.io/img/logo-dark.png").logoHeight(41)
                .and()
                .text("Welcome, {{name}}!").h1().center().and()
                .text("Thanks for trying [Product Name]. We’re thrilled to have you on board. To get the most out of [Product Name], do this primary next step:").and()
                .button("Do this Next", "http://localhost").blue().and()
                .text("For reference, here's your login information:").and()
                .attribute()
                .keyValue("Login Page", "{{login_url}}")
                .keyValue("Username", "{{username}}")
                .and()
                .html("If you have any questions, feel free to <a href=\"mailto:{{support_email}}\">email our customer success team</a>. (We're lightning quick at replying.) We also offer <a href=\"{{live_chat_url}}\">live chat</a> during business hours.",
                        """
                        If you have any questions, feel free to email our customer success team
                        (We're lightning quick at replying.) We also offer live chat during business hours.\
                        """).and()
                .text("""
                        Cheers,
                        The [Product Name] Team\
                        """).and()
                .copyright("rocketbase").url("https://www.rocketbase.io").suffix(". All rights reserved.").and()
                .footerText("""
                        [Company Name, LLC]
                        1234 Street Rd.
                        Suite 1234\
                        """).and()
                .footerImage("https://cdn.rocketbase.io/assets/loading/no-image.jpg").width(100).linkUrl("https://www.rocketbase.io").and()
                .build();
        // then
        assertThat(htmlTextEmail, notNullValue());

        sentEmail("standardTestHtml", htmlTextEmail);
    }

    @Test
    public void sideImage() {
        // given
        EmailTemplateBuilder.EmailTemplateConfigBuilder builder = EmailTemplateBuilder.builder();
        String header = "test";
        // when
        HtmlTextEmail htmlTextEmail = builder
                .header()
                .logo("https://www.rocketbase.io/img/logo-dark.png").logoHeight(41)
                .and()

                .sideImage("https://cdn.rocketbase.io/assets/loading/no-image.jpg").width(100).linkUrl("https://www.rocketbase.io")
                .imageVerticalAlign(VerticalAlignment.TOP)
                .text("Headline 123").h1().and()
                .hr().and()
                .button("Button", "http://localhost").red().right().and()
                .html("<p>My sample text with a <a href='http://localhost'>link</a>").and()
                .and()

                .text("Welcome, {{name}}!").h1().center().and()
                .text("Thanks for trying [Product Name]. We’re thrilled to have you on board. To get the most out of [Product Name], do this primary next step:").and()
                .button("Do this Next", "http://localhost").blue().and()
                .text("For reference, here's your login information:").and()

                .sideImage("https://cdn.rocketbase.io/assets/loading/no-image.jpg").width(100).linkUrl("https://www.rocketbase.io")
                .right()
                .html("<p>My sample text with a <a href='http://localhost'>link</a>").and()
                .text("Headline 123").h1().and()
                .hr().and()
                .button("Button", "http://localhost").red().center().and()
                .and()

                .text("""
                        Cheers,
                        The [Product Name] Team\
                        """).and()
                .copyright("rocketbase").url("https://www.rocketbase.io").suffix(". All rights reserved.").and()
                .footerText("""
                        [Company Name, LLC]
                        1234 Street Rd.
                        Suite 1234\
                        """).and()
                .footerImage("https://cdn.rocketbase.io/assets/loading/no-image.jpg").width(100).linkUrl("https://www.rocketbase.io").and()
                .build();
        // then
        assertThat(htmlTextEmail, notNullValue());

        sentEmail("standardTestHtml", htmlTextEmail);
    }

    @Test
    public void gallery() {
        // given
        EmailTemplateBuilder.EmailTemplateConfigBuilder builder = EmailTemplateBuilder.builder();
        String header = "gallery";
        // when
        HtmlTextEmail htmlTextEmail = builder
                .header()
                .logo("https://www.rocketbase.io/img/logo-dark.png").logoDark("https://www.rocketbase.io/img/logo-light.png").logoHeight(41)
                .and()


                .text("Impressions from somewhere").h1().center().and()
                .text("Hi hope you do well. We've prepared some pictures for you:").and()

                .gallery()
                .newRowAfter(3)
                .cellPadding(5)
                .photos(Arrays.asList("https://source.unsplash.com/random/800x600?sig=1", "https://source.unsplash.com/random/800x600?sig=2", "https://source.unsplash.com/random/800x600?sig=3", "https://source.unsplash.com/random/800x600?sig=4", "https://source.unsplash.com/random/800x600?sig=5", "https://source.unsplash.com/random/800x600?sig=6", "https://source.unsplash.com/random/800x600?sig=7"))
                .and()

                .gallery()
                .newRowAfter(3)
                .cellPadding(5)
                .photos(Arrays.asList("https://source.unsplash.com/random/800x600?sig=1"))
                .and()

                .button("Here could be an action", "http://localhost").blue().and()
                .text("Would be great if you could click :)").and()

                .gallery()
                .verticalAlign(VerticalAlignment.TOP)
                .cellPadding(5)
                .photo("https://images.unsplash.com/photo-1520116468816-95b69f847357?ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTZ8fHN1bW1lcnxlbnwwfHwwfHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=60")
                .and()
                .photo("https://images.unsplash.com/photo-1501871732394-eccc65227089?ixid=MnwxMjA3fDB8MHxzZWFyY2h8MzV8fHN1bW1lcnxlbnwwfHwwfHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=60")
                .linkUrl("https://www.rocketbase.io")
                .title("my little tile - go to rocketbase")
                .text("Hello from here").h1().and()
                .text("My longer text with some more chars should also wrapped in a proper way...").and()
                .and()
                .photo("https://images.unsplash.com/photo-1501436513145-30f24e19fcc8?ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTd8fHN1bW1lcnxlbnwwfHwwfHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=60")
                .and()
                .and()

                .text("Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.").and()


                .sideImage("https://images.unsplash.com/photo-1622267391720-bf19770a7879?ixid=MnwxMjA3fDB8MHxlZGl0b3JpYWwtZmVlZHwyfHx8ZW58MHx8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=60").width(200).linkUrl("https://www.rocketbase.io")
                .imageVerticalAlign(VerticalAlignment.TOP)
                .text("SideImage").h2().and()
                .text("some text could be below....").and()
                .hr().and()
                .button("action", "http://localhost").red().right().and()
                .and()

                .text("""
                        Cheers,
                        The [Product Name] Team\
                        """).and()
                .copyright("rocketbase").url("https://www.rocketbase.io").suffix(". All rights reserved.").and()
                .footerText("""
                        [Company Name, LLC]
                        1234 Street Rd.
                        Suite 1234\
                        """).and()
                .footerImage("https://cdn.rocketbase.io/assets/loading/no-image.jpg").width(100).linkUrl("https://www.rocketbase.io").and()
                .build();
        // then
        assertThat(htmlTextEmail, notNullValue());

        sentEmail("gallery", htmlTextEmail);
    }

    @Test
    public void standardTableTestHtml() {
        // given
        EmailTemplateBuilder.EmailTemplateConfigBuilder builder = EmailTemplateBuilder.builder();

        String header = "Invoice {{invoice_id}}";
        // when
        TbConfiguration config = TbConfiguration.newInstance();
        config.getContent().setFull(true);
        HtmlTextEmail htmlTextEmail = builder
                .configuration(config)
                .header().text(header).and()
                .text("Hi {{name}},").and()
                .text("Thanks for using [Product Name]. This is an invoice for your recent purchase").and()
                .tableSimple("#.## '€'")
                .headerRow("Description", "Amount")
                .itemRow("""
                        Special Product
                        Some extra explanations in separate line\
                        """, BigDecimal.valueOf(1333, 2))
                .itemRow("Short service", BigDecimal.valueOf(103, 1))
                .footerRow("Total", BigDecimal.valueOf(2363, 2))
                .and()
                .button("Download PDF", "http://localhost").gray().right().and()
                .text("If you have any questions about this receipt, simply reply to this email or reach out to our support team for help.").and()
                .copyright("rocketbase").url("https://www.rocketbase.io").suffix(". All rights reserved.").and()
                .footerText("""
                        [Company Name, LLC]
                        1234 Street Rd.
                        Suite 1234\
                        """).and()
                .build();
        // then
        assertThat(htmlTextEmail, notNullValue());
        sentEmail("standardTableTestHtml", htmlTextEmail);
    }

    @Test
    public void standardTableExtendedTestHtml() {
        // given
        EmailTemplateBuilder.EmailTemplateConfigBuilder builder = EmailTemplateBuilder.builder();

        String header = "Invoice {{invoice_id}}";
        // when
        TbConfiguration config = TbConfiguration.newInstance();
        config.getContent().setWidth(800);

        HtmlTextEmail htmlTextEmail = builder
                .configuration(config)
                .header().text(header).and()
                .text("Hi {{name}},").and()
                .text("Thanks for using [Product Name]. This is an invoice for your recent purchase").and()
                .tableSimpleWithImage("#.## '€'")
                .headerRow("Preview", "Description", "Amount")
                .itemRow("https://cdn.shopify.com/s/files/1/0255/1211/6260/products/TCW1142-07052_small.jpg?v=1589200198", """
                        Damen Harbour Tanktop × 1
                        QUARTZ PINK / S\
                        """, BigDecimal.valueOf(4995, 2))
                .itemRow("https://cdn.shopify.com/s/files/1/0255/1211/6260/products/TCM1886-0718_201_fdf0be52-639f-4ea8-9143-6bd75e0821b1_small.jpg?v=1583509609", """
                        Herren ten Classic T-Shirt
                        FOREST GREEN HEATHER / XL\
                        """, BigDecimal.valueOf(3995, 2))
                .itemRow("https://cdn.shopify.com/s/files/1/0255/1211/6260/products/TCM1939-0439_1332_da6f3e7c-e18d-4778-be97-c6c0b482b643_small.jpg?v=1583509671", """
                        Herren Joshua Hanfshorts
                        DARK OCEAN BLUE / XL\
                        """, BigDecimal.valueOf(6995, 2))
                .footerRow("Sum", BigDecimal.valueOf(15985, 2))
                .footerRow("Code - PLANT5", BigDecimal.valueOf(-799, 2))
                .footerRow("Total incl. Tax\n", BigDecimal.valueOf(15186, 2))
                .and()
                .button("Download PDF", "http://localhost").gray().right().and()
                .text("If you have any questions about this receipt, simply reply to this email or reach out to our support team for help.").and()
                .copyright("rocketbase").url("https://www.rocketbase.io").suffix(". All rights reserved.").and()
                .footerText("""
                        [Company Name, LLC]
                        1234 Street Rd.
                        Suite 1234\
                        """).and()
                .build();
        // then
        assertThat(htmlTextEmail, notNullValue());
        sentEmail("standardTableExtendedTestHtml", htmlTextEmail);
    }

    @Test
    public void customTableTest() {
        // given
        EmailTemplateBuilder.EmailTemplateConfigBuilder builder = EmailTemplateBuilder.builder();

// when
        TbConfiguration config = TbConfiguration.newInstance();
        config.getContent().setWidth(800);

        builder
                .configuration(config)
                .header().text("Invoice {{invoice_id}}").and()
                .text("Hi {{name}},").and()
                .text("Thanks for using [Product Name]. This is an invoice for your recent purchase");

        CustomTable customTable = new CustomTable(builder);
        customTable.itemRow(new TableCellImageSimple("https://cdn.shopify.com/s/files/1/0255/1211/6260/products/TCW1142-07052_small.jpg?v=1589200198").width(80),
                new TableCellLinkSimple("Damen Harbour Tanktop × 1\nQUARTZ PINK / S", "http://localhost/?item=1234"),
                BigDecimal.valueOf(19),
                BigDecimal.valueOf(4995, 2));
        customTable.itemRow(new TableCellImageSimple("https://cdn.shopify.com/s/files/1/0255/1211/6260/products/TCM1886-0718_201_fdf0be52-639f-4ea8-9143-6bd75e0821b1_small.jpg?v=1583509609").width(80),
                new TableCellLinkSimple("Herren ten Classic T-Shirt\nFOREST GREEN HEATHER / XL", "http://localhost/?item=4567"),
                BigDecimal.valueOf(16),
                BigDecimal.valueOf(3995, 2));
        customTable.itemRow(new TableCellImageSimple("https://cdn.shopify.com/s/files/1/0255/1211/6260/products/TCM1939-0439_1332_da6f3e7c-e18d-4778-be97-c6c0b482b643_small.jpg?v=1583509671").width(80),
                new TableCellLinkSimple("Herren Joshua Hanfshorts\nDARK OCEAN BLUE / XL", "http://localhost/?item=890"),
                BigDecimal.valueOf(19),
                BigDecimal.valueOf(6995, 2));
        customTable.footerRow(new TableCellHtmlSimple("<b>Net</b>", "Net"), new TableCellHtmlSimple("<b>159,85 €</b>", "159,85 €"));
        customTable.footerRow(new TableCellHtmlSimple("Tax 19%<br>Tax 16%<br>Shipping", "Tax 19%\nTax 16%\nShipping"), new TableCellHtmlSimple("22,78 €<br>6,39 €<br>6,90 €", "22,78 €\n6,39 €\n6,90 €"));
        customTable.footerRow(new TableCellHtmlSimple("<b>Total</b>", "Tax\n19%\n16%"), new TableCellHtmlSimple("<b>195,92 €</b>", "195,92 €"));


        builder.table(customTable)
                .button("Download PDF", "http://localhost").gray().right().and()
                .text("If you have any questions about this receipt, simply reply to this email or reach out to our support team for help.").and()
                .copyright("rocketbase").url("https://www.rocketbase.io").suffix(". All rights reserved.").and()
                .footerText("""
                        [Company Name, LLC]
                        1234 Street Rd.
                        Suite 1234\
                        """);

        HtmlTextEmail htmlTextEmail = builder.build();
        // then
        assertThat(htmlTextEmail, notNullValue());
        sentEmail("customTableTest", htmlTextEmail);
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
    public void buttonColors() {
        // given
        // when
        EmailTemplateBuilder.EmailTemplateConfigBuilder builder = EmailTemplateBuilder.builder()
                .text("some text here...").and();
        AtomicInteger stepper = new AtomicInteger(1);
        for (ColorStyle s : ColorStyleSimple.getAllPresets()) {
            builder.button("Button " + stepper.getAndIncrement(), "http://localhost:8080").color(s).center();
        }
        builder.button("Custom 1", "http://localhost:8080").color(new ColorStyleSimple("#000")).left();
        builder.button("Custom 2", "http://localhost:8080").color(new ColorStyleSimple("#ff0000")).right();
        builder.button("Custom 3", "http://localhost:8080").color(new ColorStyleSimple("00ff00")).right();
        // then
        sentEmail("buttonColors", builder.build());
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

    @Getter
    public static class CustomTable implements TableLine {

        List<List<Object>> headerRows = new ArrayList<>();
        List<List<Object>> itemRows = new ArrayList<>();
        List<List<Object>> footerRows = new ArrayList<>();
        @Getter(AccessLevel.PRIVATE)
        EmailTemplateBuilder.EmailTemplateConfigBuilder builder;
        private AtomicInteger posCounter = new AtomicInteger(1);

        public CustomTable(EmailTemplateBuilder.EmailTemplateConfigBuilder builder) {
            this.builder = builder;

            headerRows.add(Arrays.asList("Pos", "Description", "Tax", "Amount"));
        }

        @Override
        public EmailTemplateBuilder.EmailTemplateConfigBuilder and() {
            return builder;
        }

        @Override
        public HtmlTextEmail build() {
            return builder.build();
        }

        public CustomTable itemRow(TableCellImage image, TableCellLink description, BigDecimal tax, BigDecimal amount) {
            itemRows.add(Arrays.asList(posCounter.getAndIncrement(), image, description, tax, amount));
            return this;
        }

        public CustomTable footerRow(TableCellHtml label, TableCellHtml amount) {
            footerRows.add(Arrays.asList(label, amount));
            return this;
        }

        @Override
        public List<ColumnConfig> getHeader() {
            return Arrays.asList(new ColumnConfig()
                            .center(),
                    new ColumnConfig()
                            .colspan(2)
                            .width("60%"),
                    new ColumnConfig()
                            .alignment(Alignment.RIGHT),
                    new ColumnConfig()
                            .width("20%")
                            .alignment(Alignment.RIGHT));
        }

        @Override
        public List<ColumnConfig> getItem() {
            return Arrays.asList(new ColumnConfig().center(),
                    new ColumnConfig()
                            .width(90),
                    new ColumnConfig()
                            .lighter(),
                    new ColumnConfig()
                            .numberFormat("# '%'")
                            .italic()
                            .right(),
                    new ColumnConfig()
                            .numberFormat("#.## '€'")
                            .right());
        }

        @Override
        public List<ColumnConfig> getFooter() {
            return Arrays.asList(new ColumnConfig()
                            .colspan(4)
                            .alignment(Alignment.RIGHT),
                    new ColumnConfig()
                            .alignment(Alignment.RIGHT));
        }
    }
}

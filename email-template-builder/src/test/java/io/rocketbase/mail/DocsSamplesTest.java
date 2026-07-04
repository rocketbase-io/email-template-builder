package io.rocketbase.mail;

import io.rocketbase.mail.config.TbConfiguration;
import io.rocketbase.mail.model.HtmlTextEmail;
import io.rocketbase.mail.styling.ColorStyle;
import io.rocketbase.mail.styling.ColorStyleSimple;
import io.rocketbase.mail.styling.VerticalAlignment;
import io.rocketbase.mail.table.TableCellHtmlSimple;
import io.rocketbase.mail.table.TableCellImageSimple;
import io.rocketbase.mail.table.TableCellLinkSimple;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;

/**
 * renders the sample emails used within the docs (docs/src/content/docs/examples.md).
 * html output is written to target/docs-samples so screenshots can be (re)generated from it.
 */
public class DocsSamplesTest {

    private static final String LOGO_URL = "https://cdn.jsdelivr.net/gh/rocketbase-io/email-template-builder@master/assets/line-version-nightblue.svg";
    private static final String LOGO_DARK_URL = "https://cdn.jsdelivr.net/gh/rocketbase-io/email-template-builder@master/assets/line-version-white.svg";

    protected void writeSample(String name, HtmlTextEmail content) {
        try {
            Path dir = Paths.get(System.getProperty("docsSamplesDir", "target/docs-samples"));
            Files.createDirectories(dir);
            Files.write(dir.resolve(name + ".html"), content.getHtml().getBytes(StandardCharsets.UTF_8));
            Files.write(dir.resolve(name + ".txt"), content.getText().getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Test
    public void blockHeader() {
        HtmlTextEmail email = EmailTemplateBuilder.builder()
                .header()
                .logo(LOGO_URL).logoDark(LOGO_DARK_URL).logoHeight(41)
                .linkUrl("https://github.com/rocketbase-io/email-template-builder")
                .and()
                .text("A header with a logo — text-only headers work too.").and()
                .build();
        assertThat(email, notNullValue());
        writeSample("block-header", email);
    }

    @Test
    public void blockFooter() {
        HtmlTextEmail email = EmailTemplateBuilder.builder()
                .text("Footer blocks always render below the content box:").and()
                .copyright("rocketbase").url("https://www.rocketbase.io").suffix(". All rights reserved.").and()
                .footerText("Company Name, LLC\n1234 Street Rd.\nSuite 1234").and()
                .footerHr().and()
                .footerHtml("<a href=\"https://example.com/unsubscribe\">Unsubscribe</a> from these alerts.",
                        "Unsubscribe: https://example.com/unsubscribe").and()
                .build();
        assertThat(email, notNullValue());
        writeSample("block-footer", email);
    }

    @Test
    public void blockText() {
        HtmlTextEmail email = EmailTemplateBuilder.builder()
                .text("Heading 1").h1().and()
                .text("Heading 2").h2().and()
                .text("Heading 3").h3().and()
                .text("A regular paragraph with automatic line-breaks.\nSecond line included.").and()
                .text("bold").bold().and()
                .text("italic + centered").italic().center().and()
                .text("underlined + right aligned").underline().right().and()
                .text("custom color + font-size").color("#5f7bb8").fontSize("20px").and()
                .build();
        assertThat(email, notNullValue());
        writeSample("block-text", email);
    }

    @Test
    public void blockAttribute() {
        HtmlTextEmail email = EmailTemplateBuilder.builder()
                .text("For reference, here's your login information:").and()
                .attribute()
                .keyValue("Login Page", "https://example.com/login")
                .keyValue("Username", "marie")
                .keyValue("Plan", "Premium")
                .and()
                .build();
        assertThat(email, notNullValue());
        writeSample("block-attribute", email);
    }

    @Test
    public void blockImage() {
        HtmlTextEmail email = EmailTemplateBuilder.builder()
                .image("https://picsum.photos/seed/wide/1200/500")
                .width(520)
                .alt("sample image")
                .linkUrl("https://example.com")
                .center()
                .and()
                .text("An image with width, alt-text and link — aligned left, center or right.").and()
                .build();
        assertThat(email, notNullValue());
        writeSample("block-image", email);
    }

    @Test
    public void blockSideImage() {
        HtmlTextEmail email = EmailTemplateBuilder.builder()
                .sideImage("https://picsum.photos/seed/side/500/375").width(200).linkUrl("https://www.rocketbase.io")
                .imageVerticalAlign(VerticalAlignment.TOP)
                .text("Side image block").h2().and()
                .text("Combine an image with text, dividers and buttons side by side.").and()
                .hr().and()
                .button("Take action", "https://example.com").red().right().and()
                .and()
                .build();
        assertThat(email, notNullValue());
        writeSample("block-sideimage", email);
    }

    @Test
    public void blockGallery() {
        HtmlTextEmail email = EmailTemplateBuilder.builder()
                .gallery()
                .newRowAfter(3)
                .cellPadding(5)
                .photos(Arrays.asList(
                        "https://picsum.photos/seed/g1/800/600",
                        "https://picsum.photos/seed/g2/800/600",
                        "https://picsum.photos/seed/g3/800/600",
                        "https://picsum.photos/seed/g4/800/600",
                        "https://picsum.photos/seed/g5/800/600",
                        "https://picsum.photos/seed/g6/800/600"))
                .and()
                .build();
        assertThat(email, notNullValue());
        writeSample("block-gallery", email);
    }

    @Test
    public void blockDivider() {
        HtmlTextEmail email = EmailTemplateBuilder.builder()
                .text("Text above a divider…").and()
                .hr().and()
                .text("…text below it, followed by 40px vertical space:").and()
                .space().height(40).and()
                .text("Done.").and()
                .build();
        assertThat(email, notNullValue());
        writeSample("block-divider", email);
    }

    @Test
    public void blockTable() {
        HtmlTextEmail email = EmailTemplateBuilder.builder()
                .tableSimple("#,##0.00 '€'")
                .headerRow("Description", "Amount")
                .itemRow("Special Product\nSome extra explanation in a separate line", BigDecimal.valueOf(1333, 2))
                .itemRow("Short service", BigDecimal.valueOf(103, 1))
                .footerRow("Total", BigDecimal.valueOf(2363, 2))
                .and()
                .build();
        assertThat(email, notNullValue());
        writeSample("block-table", email);
    }

    @Test
    public void welcome() {
        HtmlTextEmail email = EmailTemplateBuilder.builder()
                .header()
                .logo(LOGO_URL).logoDark(LOGO_DARK_URL).logoHeight(41)
                .and()
                .text("Welcome, Marie!").h1().center().and()
                .text("Thanks for trying Product Name. We’re thrilled to have you on board. To get the most out of it, do this primary next step:").and()
                .button("Do this Next", "https://example.com/next").blue().and()
                .text("For reference, here's your login information:").and()
                .attribute()
                .keyValue("Login Page", "https://example.com/login")
                .keyValue("Username", "marie")
                .and()
                .html("If you have any questions, feel free to <a href=\"mailto:support@example.com\">email our customer success team</a>. (We're lightning quick at replying.) We also offer <a href=\"https://example.com/chat\">live chat</a> during business hours.",
                        "If you have any questions, feel free to email our customer success team.\n" +
                                "(We're lightning quick at replying.) We also offer live chat during business hours.").and()
                .text("Cheers,\nThe Product Team").and()
                .copyright("rocketbase").url("https://www.rocketbase.io").suffix(". All rights reserved.").and()
                .footerText("Company Name, LLC\n1234 Street Rd.\nSuite 1234").and()
                .build();

        assertThat(email, notNullValue());
        writeSample("welcome", email);
    }

    @Test
    public void invoice() {
        HtmlTextEmail email = EmailTemplateBuilder.builder()
                .header().text("Invoice #10456").and()
                .text("Hi Marie,").and()
                .text("Thanks for using Product Name. This is an invoice for your recent purchase:").and()
                .tableSimpleWithImage("#,##0.00 '€'")
                .headerRow("Preview", "Description", "Amount")
                .itemRow("https://picsum.photos/seed/tanktop/160/160", "Harbour Tanktop × 1\nQUARTZ PINK / S", BigDecimal.valueOf(4995, 2))
                .itemRow("https://picsum.photos/seed/tshirt/160/160", "Classic T-Shirt × 1\nFOREST GREEN / XL", BigDecimal.valueOf(3995, 2))
                .itemRow("https://picsum.photos/seed/shorts/160/160", "Joshua Hemp Shorts × 1\nDARK OCEAN BLUE / XL", BigDecimal.valueOf(6995, 2))
                .footerRow("Sum", BigDecimal.valueOf(15985, 2))
                .footerRow("Code - PLANT5", BigDecimal.valueOf(-799, 2))
                .footerRow("Total incl. Tax", BigDecimal.valueOf(15186, 2))
                .and()
                .button("Download PDF", "https://example.com/invoice.pdf").gray().right().and()
                .text("If you have any questions about this receipt, simply reply to this email or reach out to our support team for help.").and()
                .copyright("rocketbase").url("https://www.rocketbase.io").suffix(". All rights reserved.").and()
                .footerText("Company Name, LLC\n1234 Street Rd.\nSuite 1234").and()
                .build();

        assertThat(email, notNullValue());
        writeSample("invoice", email);
    }

    @Test
    public void frameless() {
        TbConfiguration config = TbConfiguration.newInstanceFrameless();
        HtmlTextEmail email = EmailTemplateBuilder.builder()
                .configuration(config)
                .preheader("You are invited to our summer party 🎉")
                .text("Hi Marie,").and()
                .text("we'd love to see you at our summer party next Friday at 6pm — rooftop, drinks and good music included.").and()
                .button("Save my spot", "https://example.com/rsvp").blue().left().and()
                .text("Casual dress code, bring a friend if you like.\n\nCheers,\nMarten").and()
                .copyright("rocketbase").url("https://www.rocketbase.io").and()
                .build();

        assertThat(email, notNullValue());
        writeSample("frameless", email);
    }

    @Test
    public void buttons() {
        EmailTemplateBuilder.EmailTemplateConfigBuilder builder = EmailTemplateBuilder.builder()
                .header().text("Button styles").and()
                .text("All color presets plus custom colors:").and();
        AtomicInteger stepper = new AtomicInteger(1);
        for (ColorStyle s : ColorStyleSimple.getAllPresets()) {
            builder.button("Preset " + stepper.getAndIncrement(), "https://example.com").color(s).center();
        }
        builder.button("Custom left", "https://example.com").color(new ColorStyleSimple("#1c1c28")).left();
        builder.button("Custom right", "https://example.com").color(new ColorStyleSimple("#5f7bb8")).right();

        HtmlTextEmail email = builder.build();
        assertThat(email, notNullValue());
        writeSample("buttons", email);
    }

    @Test
    public void customTable() {
        TbConfiguration config = TbConfiguration.newInstance();
        config.getContent().setWidth(800);

        EmailTemplateBuilder.EmailTemplateConfigBuilder builder = EmailTemplateBuilder.builder()
                .configuration(config)
                .header().text("Invoice #10456").and()
                .text("Hi Marie,").and()
                .text("Thanks for using Product Name. This is an invoice for your recent purchase:").and();

        EmailTemplateBuilderTest.CustomTable customTable = new EmailTemplateBuilderTest.CustomTable(builder);
        customTable.itemRow(new TableCellImageSimple("https://picsum.photos/seed/tanktop/160/160").width(80),
                new TableCellLinkSimple("Harbour Tanktop × 1\nQUARTZ PINK / S", "https://example.com/?item=1234"),
                BigDecimal.valueOf(19),
                BigDecimal.valueOf(4995, 2));
        customTable.itemRow(new TableCellImageSimple("https://picsum.photos/seed/tshirt/160/160").width(80),
                new TableCellLinkSimple("Classic T-Shirt × 1\nFOREST GREEN / XL", "https://example.com/?item=4567"),
                BigDecimal.valueOf(16),
                BigDecimal.valueOf(3995, 2));
        customTable.footerRow(new TableCellHtmlSimple("<b>Net</b>", "Net"), new TableCellHtmlSimple("<b>109,90 €</b>", "109,90 €"));
        customTable.footerRow(new TableCellHtmlSimple("Tax 19%<br>Tax 16%<br>Shipping", "Tax 19%\nTax 16%\nShipping"), new TableCellHtmlSimple("15,65 €<br>5,52 €<br>6,90 €", "15,65 €\n5,52 €\n6,90 €"));
        customTable.footerRow(new TableCellHtmlSimple("<b>Total</b>", "Total"), new TableCellHtmlSimple("<b>137,97 €</b>", "137,97 €"));

        HtmlTextEmail email = builder.table(customTable)
                .button("Download PDF", "https://example.com/invoice.pdf").gray().right().and()
                .copyright("rocketbase").url("https://www.rocketbase.io").suffix(". All rights reserved.").and()
                .footerText("Company Name, LLC\n1234 Street Rd.\nSuite 1234").and()
                .build();

        assertThat(email, notNullValue());
        writeSample("custom-table", email);
    }

    @Test
    public void gallery() {
        HtmlTextEmail email = EmailTemplateBuilder.builder()
                .header().text("Impressions").and()
                .text("Impressions from somewhere").h1().center().and()
                .text("Hi, hope you are doing well. We've prepared some pictures for you:").and()
                .gallery()
                .newRowAfter(3)
                .cellPadding(5)
                .photos(Arrays.asList(
                        "https://picsum.photos/seed/g1/800/600",
                        "https://picsum.photos/seed/g2/800/600",
                        "https://picsum.photos/seed/g3/800/600",
                        "https://picsum.photos/seed/g4/800/600",
                        "https://picsum.photos/seed/g5/800/600",
                        "https://picsum.photos/seed/g6/800/600"))
                .and()
                .sideImage("https://picsum.photos/seed/side/500/375").width(200).linkUrl("https://www.rocketbase.io")
                .imageVerticalAlign(VerticalAlignment.TOP)
                .text("Side image block").h2().and()
                .text("Combine an image with text, dividers and buttons side by side.").and()
                .hr().and()
                .button("Take action", "https://example.com").red().right().and()
                .and()
                .copyright("rocketbase").url("https://www.rocketbase.io").and()
                .build();

        assertThat(email, notNullValue());
        writeSample("gallery", email);
    }
}

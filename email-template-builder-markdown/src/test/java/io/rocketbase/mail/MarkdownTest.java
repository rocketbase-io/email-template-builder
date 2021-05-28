package io.rocketbase.mail;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
@Slf4j
public class MarkdownTest {

    @Test
    public void testSome() {
        // given
        String input = "This is *Sparta*\n" +
                "# Heading 1\n" +
                "## Heading 2\n" +
                "### Heading 3\n" +
                "#### Heading 4\n";
        // when
        MarkdownLine markdownLine = new MarkdownLine(input);

        // then
        assertThat(markdownLine.getMarkdown(), notNullValue());
        assertThat(markdownLine.getHtml(), notNullValue());
        assertThat(markdownLine.getMarkdown(), equalTo(input));
        assertThat(markdownLine.getHtml(), equalTo("<p style=\"font-family: 'Nunito Sans', Helvetica, Arial, sans-serif; margin: .4em 0 1.1875em; font-size: 16px; line-height: 1.625; color: #51545E\">This is <em>Sparta</em></p>\n" +
                "<h1 style=\"font-family: 'Nunito Sans', Helvetica, Arial, sans-serif; font-size: 22px; color: #333333\">Heading 1</h1>\n" +
                "<h2 style=\"font-family: 'Nunito Sans', Helvetica, Arial, sans-serif; font-size: 16px; color: #333333\">Heading 2</h2>\n" +
                "<h3 style=\"font-family: 'Nunito Sans', Helvetica, Arial, sans-serif; font-size: 14px; color: #333333\">Heading 3</h3>\n" +
                "<h4 style=\"font-family: 'Nunito Sans', Helvetica, Arial, sans-serif; font-size: 14px; color: #333333\">Heading 4</h4>\n"));
    }
}

package io.rocketbase.commons.email;

import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class TextLineTest {

    @Test
    public void injectStylingAndExtractBr() {
        // given
        String input = "sample text<br><a href=\"http://sample.io\" class=\"btn-button\">sample button<i class=\"icon-click\"></i></a>";
        // when
        TextLine textLine = new TextLine(null, input);
        // then
        assertThat(textLine.asHtml, equalTo(true));
        assertThat(textLine.text.replaceAll(System.getProperty("line.separator"), ""), equalTo("sample text<br><a href=\"http://sample.io\" class=\"btn-button\" style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; color: #348eda; text-decoration: underline; margin: 0;\">sample button<i class=\"icon-click\"></i></a>"));
        assertThat(textLine.escapedText, equalTo("sample text\nsample button -> http://sample.io"));
    }

    @Test
    public void extratPWithNewLine() {
        // given
        String input = "<p>sample text</p><p>Another Line</p>";
        // when
        TextLine textLine = new TextLine(null, input);
        // then
        assertThat(textLine.asHtml, equalTo(true));
        assertThat(textLine.text.replaceAll("\n", ""), equalTo(input));
        assertThat(textLine.escapedText, equalTo("sample text\nAnother Line"));
    }

    @Test
    public void simpleText() {
        // given
        String input = "sample text";
        // when
        TextLine textLine = new TextLine(null, input);
        // then
        assertThat(textLine.asHtml, equalTo(false));
        assertThat(textLine.text, equalTo(input));
        assertThat(textLine.escapedText, equalTo(input));
    }
}
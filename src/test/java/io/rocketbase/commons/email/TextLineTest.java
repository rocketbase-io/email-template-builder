package io.rocketbase.commons.email;

import io.rocketbase.commons.email.template.TemplateLine;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class TextLineTest {

    @Test
    public void injectStylingAndExtractBr() {
        // given
        String input = "sample text<br><a href=\"http://sample.io\" class=\"btn-button\">sample button<i class=\"icon-click\"></i></a>";
        // when
        TextLine textLine = new TextLine(TemplateLine.TemplateLineType.TEXT, null, input);
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
        TextLine textLine = new TextLine(TemplateLine.TemplateLineType.TEXT, null, input);
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
        TextLine textLine = new TextLine(TemplateLine.TemplateLineType.TEXT, null, input);
        // then
        assertThat(textLine.asHtml, equalTo(false));
        assertThat(textLine.text, equalTo(input));
        assertThat(textLine.escapedText, equalTo(input));
    }

    @Test
    public void forceAsText() {
        // given
        String input = "sample <b>bold</b> text";
        // when
        TextLine textLine = new TextLine(TemplateLine.TemplateLineType.TEXT, null, input, false);
        // then
        assertThat(textLine.asHtml, equalTo(false));
        assertThat(textLine.text, equalTo("sample &lt;b&gt;bold&lt;/b&gt; text"));
        assertThat(textLine.escapedText, equalTo(input));
    }

    @Test
    public void forceAsHtmlLineBreak() {
        // given
        String input = "sample text<br>with line break";
        // when
        TextLine textLine = new TextLine(TemplateLine.TemplateLineType.TEXT, null, input, true);
        // then
        assertThat(textLine.asHtml, equalTo(true));
        assertThat(textLine.text, equalTo("sample text\n<br>with line break"));
        assertThat(textLine.escapedText, equalTo("sample text\nwith line break"));
    }

    @Test
    public void forceAsHtmlTags() {
        // given
        String input = "sample <b>bold</b> text";
        // when
        TextLine textLine = new TextLine(TemplateLine.TemplateLineType.TEXT, null, input, true);
        // then
        assertThat(textLine.asHtml, equalTo(true));
        assertThat(textLine.text, equalTo("sample <b>bold</b> text"));
        assertThat(textLine.escapedText, equalTo("sample bold text"));
    }

    @Test
    public void forceAsHtmlQuoting() {
        // given
        String input = "sample &Uuml;mlaut text";
        // when
        TextLine textLine = new TextLine(TemplateLine.TemplateLineType.TEXT, null, input, true);
        // then
        assertThat(textLine.asHtml, equalTo(true));
        assertThat(textLine.text, equalTo("sample Ümlaut text"));
        assertThat(textLine.escapedText, equalTo("sample Ümlaut text"));
    }
}

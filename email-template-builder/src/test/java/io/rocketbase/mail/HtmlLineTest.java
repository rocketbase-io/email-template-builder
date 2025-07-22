package io.rocketbase.mail;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class HtmlLineTest {

    @Test
    void testHtmlExtraction() {
        HtmlLine htmlLine = new HtmlLine(null, "<p>My very <i>easy</i> example that should <div style='background: red'>work</div></p>also the text version should look<br>very fine... See also <a href='http://test.de' style='color: red'>test.de</a><br>see you soon :)");
        assertThat(htmlLine.getText(), equalTo("My very easy example that should work\n" +
                "also the text version should look\n" +
                "very fine... See also test.de -> http://test.de\n" +
                "see you soon :)"));
    }
}
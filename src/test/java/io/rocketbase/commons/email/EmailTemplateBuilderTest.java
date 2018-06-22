package io.rocketbase.commons.email;

import io.rocketbase.commons.email.model.HtmlTextEmail;
import org.junit.Test;

import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

public class EmailTemplateBuilderTest {

    @Test
    public void builder() {
        // given
        EmailTemplateBuilder builder = EmailTemplateBuilder.builder();
        // when
        HtmlTextEmail htmlTextEmail = builder.header("test")
                .addText("sample-text")
                .addButton("button 1", "http://adasd")
                .copyright("rocketbase", "https://www.rocketbase.io")
                .build();
        // then
        assertThat(htmlTextEmail, notNullValue());
    }
}
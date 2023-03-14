package io.rocketbase.mail;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Objects;

import static org.hamcrest.MatcherAssert.assertThat;

public class AttibuteLineTest {
    @Test
    public void standardTestHtml() {
        // given
        EmailTemplateBuilder.EmailTemplateConfigBuilder builder = EmailTemplateBuilder.builder();
        AttributeLine attributes = builder
                .attribute()
                .map(new HashMap<String, String>() {{
                    put("a", "b");
                    put("c", "d");
                }});
        assertThat("Attributes are not empty", !attributes.map.isEmpty());
        assertThat("Attributes have the correct values", Objects.equals(attributes.map.get("a"), "b") && Objects.equals(attributes.map.get("c"), "d"));
    }
}

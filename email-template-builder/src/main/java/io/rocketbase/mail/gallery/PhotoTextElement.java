package io.rocketbase.mail.gallery;

import io.rocketbase.mail.config.TbConfiguration;
import io.rocketbase.mail.line.AbstractTextLine;
import io.rocketbase.mail.model.HtmlTextEmail;
import io.rocketbase.mail.styling.FontWeight;
import lombok.Getter;

@Getter
public class PhotoTextElement extends AbstractTextLine<PhotoTextElement> {

    private final PhotoElement parent;
    private final TbConfiguration configuration;

    public PhotoTextElement(PhotoElement parent, TbConfiguration configuration, String text) {
        super(text);
        this.parent = parent;
        this.configuration = configuration;
    }

    public PhotoTextElement h1() {
        tbFont(configuration.getFont().getH1());
        this.fontWeight = FontWeight.BOLD;
        return this;
    }

    public PhotoTextElement h2() {
        tbFont(configuration.getFont().getH2());
        this.fontWeight = FontWeight.BOLD;
        return this;
    }

    public PhotoTextElement h3() {
        tbFont(configuration.getFont().getH3());
        this.fontWeight = FontWeight.BOLD;
        return this;
    }

    public PhotoTextElement sub() {
        tbFont(configuration.getFont().getSub());
        return this;
    }

    public PhotoElement and() {
        return parent;
    }

    public HtmlTextEmail build() {
        return parent.build();
    }
}

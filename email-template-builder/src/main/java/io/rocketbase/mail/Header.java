package io.rocketbase.mail;

import io.rocketbase.mail.model.HtmlTextEmail;
import lombok.AccessLevel;
import lombok.Getter;

@Getter
public class Header {

    String text;

    String logo;
    String logoWidth;
    String logoHeight;

    String linkUrl;

    @Getter(AccessLevel.PRIVATE)
    EmailTemplateBuilder.EmailTemplateConfigBuilder builder;

    Header(EmailTemplateBuilder.EmailTemplateConfigBuilder builder) {
        this.builder = builder;
    }

    /**
     * will replace text so that text is only alt for image
     */
    public Header logo(String logo) {
        this.logo = logo;
        return this;
    }

    /**
     * could be replaced by logo - then alt-text
     */
    public Header text(String text) {
        this.text = text;
        return this;
    }

    public Header linkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
        return this;
    }

    /**
     * width in pixel
     */
    public Header logoWidth(int logoWidth) {
        this.logoWidth = String.valueOf(logoWidth);
        return this;
    }

    /**
     * height in pixel
     */
    public Header logoHeight(int logoHeight) {
        this.logoHeight = String.valueOf(logoHeight);
        return this;
    }

    /**
     * width in percentage please add %
     */
    public Header logoWidth(String logoWidth) {
        this.logoWidth = logoWidth;
        return this;
    }

    /**
     * height in percentage please add %
     */
    public Header logoHeight(String logoHeight) {
        this.logoHeight = logoHeight;
        return this;
    }

    public EmailTemplateBuilder.EmailTemplateConfigBuilder and() {
        return builder;
    }

    public HtmlTextEmail build() {
        return builder.build();
    }

}

package io.rocketbase.mail.line;

import lombok.Getter;

@Getter
public class AbstractImageLine<E extends AbstractImageLine> {

    protected final String src;
    protected String alt;
    protected String width;
    protected String height;
    protected String title;
    protected String linkUrl;
    protected String margin;

    public AbstractImageLine(String src) {
        this.src = src;
    }

    public E alt(String alt) {
        this.alt = alt;
        return (E) this;
    }

    public E title(String title) {
        this.title = title;
        return (E) this;
    }

    public E linkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
        return (E) this;
    }

    /**
     * width in pixel
     */
    public E width(int width) {
        this.width = String.valueOf(width);
        return (E) this;
    }

    /**
     * height in pixel
     */
    public E height(int height) {
        this.height = String.valueOf(height);
        return (E) this;
    }

    /**
     * width in percentage please add %
     */
    public E width(String width) {
        this.width = width;
        return (E) this;
    }

    /**
     * height in percentage please add %
     */
    public E height(String height) {
        this.height = height;
        return (E) this;
    }

    /**
     * default 20px auto
     */
    public E margin(String margin) {
        this.margin = margin;
        return (E) this;
    }
}

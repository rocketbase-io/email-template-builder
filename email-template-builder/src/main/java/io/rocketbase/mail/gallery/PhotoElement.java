package io.rocketbase.mail.gallery;

import io.rocketbase.mail.GalleryLine;
import io.rocketbase.mail.config.TbConfiguration;
import io.rocketbase.mail.model.HtmlTextEmail;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
public class PhotoElement {

    private final GalleryLine parent;
    private final TbConfiguration configuration;

    protected List<PhotoTextElement> textLines = new ArrayList<>();

    protected final String src;
    protected String alt;
    protected String title;
    protected String linkUrl;

    public PhotoElement(GalleryLine parent, TbConfiguration configuration, String src) {
        this.src = src;
        this.parent = parent;
        this.configuration = configuration;
    }

    public PhotoElement alt(String alt) {
        this.alt = alt;
        return this;
    }

    public PhotoElement title(String title) {
        this.title = title;
        return this;
    }

    public PhotoElement linkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
        return this;
    }

    public PhotoTextElement text(String text) {
        PhotoTextElement line = new PhotoTextElement(this, configuration, text);
        textLines.add(line);
        return line;
    }

    public PhotoElement texts(Collection<String> texts) {
        for (String t : texts) {
            PhotoTextElement line = new PhotoTextElement(this, configuration, t);
            textLines.add(line);
        }
        return this;
    }

    public GalleryLine and() {
        return parent;
    }

    public HtmlTextEmail build() {
        return parent.build();
    }


}

package io.rocketbase.mail;

import io.rocketbase.mail.gallery.PhotoElement;
import io.rocketbase.mail.model.HtmlTextEmail;
import io.rocketbase.mail.styling.Alignment;
import io.rocketbase.mail.styling.VerticalAlignment;
import lombok.AccessLevel;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
public class GalleryLine implements TemplateLine {

    @Getter(AccessLevel.PRIVATE)
    EmailTemplateBuilder.EmailTemplateConfigBuilder builder;

    protected List<PhotoElement> photoElements = new ArrayList<>();

    protected String margin;
    protected Integer cellPadding = 0;
    protected Integer cellSpacing = 0;
    protected Integer newRowAfter;

    protected Alignment alignment;
    protected VerticalAlignment contentVerticalAlign = VerticalAlignment.MIDDLE;

    GalleryLine(EmailTemplateBuilder.EmailTemplateConfigBuilder builder) {
        this.builder = builder;
    }

    public PhotoElement photo(String src) {
        PhotoElement line = new PhotoElement(this, builder.getConfiguration(), src);
        photoElements.add(line);
        return line;
    }

    public GalleryLine photos(Collection<String> srcs) {
        for (String src : srcs) {
            PhotoElement line = new PhotoElement(this, builder.getConfiguration(), src);
            photoElements.add(line);
        }
        return this;
    }

    /**
     * default 20px auto
     */
    public GalleryLine margin(String margin) {
        this.margin = margin;
        return this;
    }


    /**
     * padding between photo-cells default 0
     */
    public GalleryLine cellPadding(Integer cellPadding) {
        this.cellPadding = cellPadding;
        return this;
    }

    /**
     * after count of x photos new row should get generated<br>
     * default 3
     */
    public GalleryLine newRowAfter(Integer newRowAfter) {
        this.newRowAfter = newRowAfter;
        return this;
    }

    /**
     * spacing between photo-cells default 0
     */
    public GalleryLine cellSpacing(Integer cellSpacing) {
        this.cellSpacing = cellSpacing;
        return this;
    }

    /**
     * alignment of each photo within it's cell
     */
    public GalleryLine alignment(Alignment alignment) {
        this.alignment = alignment;
        return this;
    }

    /**
     * vertical alignment of each photo within it's cell
     */
    public GalleryLine verticalAlign(VerticalAlignment verticalAlignment) {
        this.contentVerticalAlign = verticalAlignment;
        return this;
    }

    /**
     * used only by template to easyily fill empty cells
     */
    public List<String> getEmptyCells() {
        List<String> result = new ArrayList<>();
        if (photoElements != null && !photoElements.isEmpty() && newRowAfter != null) {
            int startModulo = (photoElements.size() % (newRowAfter + 1));
            if (startModulo > 0) {
                for (int x = startModulo; x < newRowAfter; x++) {
                    result.add("");
                }
            }
        }
        return result;
    }

    /**
     * used only by template to easyily fill empty cells
     */
    public String getColumnWidth() {
        return newRowAfter == null ? null : (Math.round(10000.0/(newRowAfter.intValue() * 1.0))/100.0+"%");
    }

    @Override
    public TemplateLineType getType() {
        return TemplateLineType.GALLERY;
    }

    @Override
    public EmailTemplateBuilder.EmailTemplateConfigBuilder and() {
        return builder;
    }

    @Override
    public HtmlTextEmail build() {
        return builder.build();
    }
}

package io.rocketbase.mail.side;

import io.rocketbase.mail.SideImageLine;
import io.rocketbase.mail.line.AbstractHtmlLine;
import io.rocketbase.mail.model.HtmlTextEmail;

public class SideHtmlLine extends AbstractHtmlLine<SideHtmlLine> implements SideContentLine {

    private final SideImageLine parent;

    public SideHtmlLine(SideImageLine parent, String html, String text) {
        super(html, text);
        this.parent = parent;
    }

    public SideHtmlLine(SideImageLine parent, String html) {
        super(html);
        this.parent = parent;
    }

    @Override
    public SideContentLineType getType() {
        return SideContentLineType.HTML;
    }

    @Override
    public SideImageLine and() {
        return parent;
    }

    @Override
    public HtmlTextEmail build() {
        return parent.build();
    }
}

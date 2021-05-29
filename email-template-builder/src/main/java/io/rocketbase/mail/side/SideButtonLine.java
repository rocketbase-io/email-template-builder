package io.rocketbase.mail.side;

import io.rocketbase.mail.SideImageLine;
import io.rocketbase.mail.line.AbstractButtonLine;
import io.rocketbase.mail.model.HtmlTextEmail;

public class SideButtonLine extends AbstractButtonLine<SideButtonLine> implements SideContentLine {

    private final SideImageLine parent;

    public SideButtonLine(SideImageLine parent, String text, String url) {
        super(text, url);
        this.parent = parent;
    }

    @Override
    public SideContentLineType getType() {
        return SideContentLineType.BUTTON;
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

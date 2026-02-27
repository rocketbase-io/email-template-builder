package io.rocketbase.mail.side;

import io.rocketbase.mail.SideImageLine;
import io.rocketbase.mail.line.AbstractSpaceLine;
import io.rocketbase.mail.model.HtmlTextEmail;

public class SideSpaceLine extends AbstractSpaceLine<SideSpaceLine> implements SideContentLine {

    private final SideImageLine parent;

    public SideSpaceLine(SideImageLine parent) {
        super();
        this.parent = parent;
    }

    public SideSpaceLine(SideImageLine parent, Integer height) {
        super(height);
        this.parent = parent;
    }

    @Override
    public SideContentLineType getType() {
        return SideContentLineType.SPACE;
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

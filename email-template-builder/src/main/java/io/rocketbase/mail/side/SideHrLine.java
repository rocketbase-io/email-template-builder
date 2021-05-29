package io.rocketbase.mail.side;

import io.rocketbase.mail.SideImageLine;
import io.rocketbase.mail.line.AbstractHrLine;
import io.rocketbase.mail.model.HtmlTextEmail;

public class SideHrLine extends AbstractHrLine<SideHrLine> implements SideContentLine {

    private final SideImageLine parent;

    public SideHrLine(SideImageLine parent) {
        this.parent = parent;
    }

    @Override
    public SideContentLineType getType() {
        return SideContentLineType.HR;
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

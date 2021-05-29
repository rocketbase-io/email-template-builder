package io.rocketbase.mail.side;

import io.rocketbase.mail.SideImageLine;
import io.rocketbase.mail.config.TbConfiguration;
import io.rocketbase.mail.line.AbstractTextLine;
import io.rocketbase.mail.model.HtmlTextEmail;
import io.rocketbase.mail.styling.FontWeight;

public class SideTextLine extends AbstractTextLine<SideTextLine> implements SideContentLine {

    private final SideImageLine parent;
    private final TbConfiguration configuration;

    public SideTextLine(SideImageLine parent, TbConfiguration configuration, String text) {
        super(text);
        this.parent = parent;
        this.configuration = configuration;
    }

    public SideTextLine h1() {
        tbFont(configuration.getFont().getH1());
        this.fontWeight = FontWeight.BOLD;
        return this;
    }

    public SideTextLine h2() {
        tbFont(configuration.getFont().getH2());
        this.fontWeight = FontWeight.BOLD;
        return this;
    }

    public SideTextLine h3() {
        tbFont(configuration.getFont().getH3());
        this.fontWeight = FontWeight.BOLD;
        return this;
    }

    public SideTextLine sub() {
        tbFont(configuration.getFont().getSub());
        return this;
    }

    @Override
    public SideContentLineType getType() {
        return SideContentLineType.TEXT;
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

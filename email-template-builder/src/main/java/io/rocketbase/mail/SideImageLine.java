package io.rocketbase.mail;

import io.rocketbase.mail.EmailTemplateBuilder.EmailTemplateConfigBuilder;
import io.rocketbase.mail.line.AbstractImageLine;
import io.rocketbase.mail.model.HtmlTextEmail;
import io.rocketbase.mail.side.*;
import io.rocketbase.mail.styling.Side;
import lombok.AccessLevel;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class SideImageLine extends AbstractImageLine<SideImageLine> implements TemplateLine {


    @Getter(AccessLevel.PRIVATE)
    EmailTemplateConfigBuilder builder;

    protected Side side = Side.LEFT;

    protected List<SideContentLine> contentLines = new ArrayList<>();

    SideImageLine(EmailTemplateConfigBuilder builder, String src) {
        super(src);
        this.builder = builder;
    }

    public SideImageLine side(Side side) {
        this.side = side;
        return this;
    }

    public SideImageLine left() {
        this.side = Side.LEFT;
        return this;
    }

    public SideImageLine right() {
        this.side = Side.RIGHT;
        return this;
    }

    public SideHrLine hr() {
        SideHrLine line = new SideHrLine(this);
        contentLines.add(line);
        return line;
    }

    public SideTextLine text(String text) {
        SideTextLine line = new SideTextLine(this, builder.getConfiguration(), text);
        contentLines.add(line);
        return line;
    }

    public SideHtmlLine html(String html, String text) {
        SideHtmlLine line = new SideHtmlLine(this, html, text);
        contentLines.add(line);
        return line;
    }

    /**
     * @param html text version will get extracted out of html
     */
    public SideHtmlLine html(String html) {
        SideHtmlLine line = new SideHtmlLine(this, html);
        contentLines.add(line);
        return line;
    }

    public SideButtonLine button(String text, String url) {
        SideButtonLine line = new SideButtonLine(this, text, url);
        contentLines.add(line);
        return line;
    }

    @Override
    public TemplateLineType getType() {
        return TemplateLineType.SIDE_IMAGE;
    }

    @Override
    public EmailTemplateConfigBuilder and() {
        return builder;
    }

    @Override
    public HtmlTextEmail build() {
        return builder.build();
    }
}

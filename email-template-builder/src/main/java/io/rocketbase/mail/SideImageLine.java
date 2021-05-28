package io.rocketbase.mail;

import io.rocketbase.mail.EmailTemplateBuilder.EmailTemplateConfigBuilder;
import io.rocketbase.mail.line.AbstractImageLine;
import io.rocketbase.mail.model.HtmlTextEmail;
import io.rocketbase.mail.styling.Side;
import lombok.AccessLevel;
import lombok.Getter;

@Getter
public class SideImageLine extends AbstractImageLine<SideImageLine> implements TemplateLine {


    @Getter(AccessLevel.PRIVATE)
    EmailTemplateConfigBuilder builder;

    protected Side side;

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

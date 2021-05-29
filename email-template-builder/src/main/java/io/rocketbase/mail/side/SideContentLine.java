package io.rocketbase.mail.side;

import io.rocketbase.mail.SideImageLine;
import io.rocketbase.mail.model.HtmlTextEmail;

public interface SideContentLine {

    SideContentLineType getType();

    SideImageLine and();

    HtmlTextEmail build();

    enum SideContentLineType {

        HR, TEXT, HTML, BUTTON;
    }
}

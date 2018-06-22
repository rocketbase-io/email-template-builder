package io.rocketbase.commons.email.template;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ButtonLine implements TemplateLine {

    private final String text;
    private final String url;
    private final ColorStyle color;

    @Override
    public TemplateLineType getType() {
        return TemplateLineType.BUTTON;
    }
}

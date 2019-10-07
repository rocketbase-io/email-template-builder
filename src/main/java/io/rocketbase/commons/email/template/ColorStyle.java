package io.rocketbase.commons.email.template;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ColorStyle {

    public static ColorStyle BASE_STYLE = new ColorStyle("ffffff", "457B9D");
    public static ColorStyle WARNING_STYLE = new ColorStyle("ffffff", "ff9f00");

    private final String text;

    private final String bg;

}

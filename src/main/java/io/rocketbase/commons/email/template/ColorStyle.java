package io.rocketbase.commons.email.template;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ColorStyle {

    public static ColorStyle BASE_STYLE = new ColorStyle("ffffff", "457B9D");

    private final String text;

    private final String bg;

}

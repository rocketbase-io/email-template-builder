package io.rocketbase.commons.email.template;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class HeaderConfig {

    private final String title;
    private final ColorStyle color;
}

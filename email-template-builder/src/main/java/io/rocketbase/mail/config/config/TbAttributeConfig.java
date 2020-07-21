package io.rocketbase.mail.config.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class TbAttributeConfig {

    public static final TbAttributeConfig DEFAULT = new TbAttributeConfig("0 0 21px",
            "#F4F4F7",
            "16px");

    private final String margin;
    private final String background;
    private final String padding;

    public TbAttributeConfig(TbAttributeConfig other) {
        this.margin = other.margin;
        this.background = other.background;
        this.padding = other.padding;
    }
}

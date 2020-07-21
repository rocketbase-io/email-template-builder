package io.rocketbase.mail.config.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class TbHeaderConfig {

    public static final TbHeaderConfig DEFAULT = new TbHeaderConfig("25px 0",
            "center",
            "16px",
            "bold",
            "#A8AAAF",
            "0 1px 0 white");

    private final String padding;
    private final String align;
    private final String size;
    private final String weight;
    private final String color;
    private final String textShadow;

    public TbHeaderConfig(TbHeaderConfig other) {
        this.padding = other.padding;
        this.align = other.align;
        this.size = other.size;
        this.weight = other.weight;
        this.color = other.color;
        this.textShadow = other.textShadow;
    }
}

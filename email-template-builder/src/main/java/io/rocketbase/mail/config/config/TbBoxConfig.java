package io.rocketbase.mail.config.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class TbBoxConfig {

    public static final TbBoxConfig DEFAULT = new TbBoxConfig("24px",
            "#F4F4F7",
            "2px dashed #CBCCCF",
            new TbBoxDark("#222"));

    private final String padding;
    private final String background;
    private final String border;
    private final TbBoxDark dark;

    public TbBoxConfig(TbBoxConfig other) {
        this.padding = other.padding;
        this.background = other.background;
        this.border = other.border;
        this.dark = other.dark;
    }

    @Getter
    @RequiredArgsConstructor
    public static class TbBoxDark {
        private final String background;
    }
}

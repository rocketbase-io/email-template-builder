package io.rocketbase.mail.config.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class TbBodyConfig {

    public static final TbBodyConfig DEFAULT = new TbBodyConfig("#F4F4F7",
            new TbBodyBorder("1px", "#EAEAEC"),
            new TbBodyDark("#333333", "#FFF"));

    private final String background;
    private final TbBodyBorder border;
    private final TbBodyDark dark;

    public TbBodyConfig(TbBodyConfig other) {
        this.background = other.background;
        this.border = other.border;
        this.dark = other.dark;
    }

    @Getter
    @RequiredArgsConstructor
    public static class TbBodyBorder {
        private final String size;
        private final String color;
    }

    @Getter
    @RequiredArgsConstructor
    public static class TbBodyDark {
        private final String background;
        private final String color;
    }
}

package io.rocketbase.mail.config.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class TbFooterConfig {

    public static final TbFooterConfig DEFAULT = new TbFooterConfig("#6B6E76",
            new TbFooterLink("#6B6E76", "underline"));

    private final String color;
    private final TbFooterLink link;

    public TbFooterConfig(TbFooterConfig other) {
        this.color = other.color;
        this.link = other.link;
    }

    @Getter
    @RequiredArgsConstructor
    public static class TbFooterLink {
        private final String color;
        private final String textDecoration;
    }
}

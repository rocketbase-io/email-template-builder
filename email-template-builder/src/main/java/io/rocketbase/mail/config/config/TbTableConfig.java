package io.rocketbase.mail.config.config;

import io.rocketbase.mail.config.base.TbBorderColorSize;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class TbTableConfig {

    public static final TbTableConfig DEFAULT = new TbTableConfig(new TbTableItem("#51545E", "15px", "18px"),
            new TbBorderColorSize("#EAEAEC", "1px"),
            new TbTableHeading("#85878E", "12px"),
            new TbTableTotal("#333333"));

    private final TbTableItem item;
    private final TbBorderColorSize border;
    private final TbTableHeading heading;
    private final TbTableTotal total;

    public TbTableConfig(TbTableConfig other) {
        this.item = other.item;
        this.border = other.border;
        this.heading = other.heading;
        this.total = other.total;
    }

    @Getter
    @RequiredArgsConstructor
    public static class TbTableItem {
        private final String color;
        private final String size;
        private final String lineHeight;
    }

    @Getter
    @RequiredArgsConstructor
    public static class TbTableHeading {
        private final String color;
        private final String size;
    }

    @Getter
    @RequiredArgsConstructor
    public static class TbTableTotal {
        private final String color;
    }
}

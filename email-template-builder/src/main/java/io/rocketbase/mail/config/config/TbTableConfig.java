package io.rocketbase.mail.config.config;

import io.rocketbase.mail.config.base.TbBorderColorSize;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TbTableConfig {

    static final TbTableConfig DEFAULT = new TbTableConfig(new TbTableItem("#51545E", "15px", "18px"),
            new TbBorderColorSize("#EAEAEC", "1px"),
            new TbTableHeading("#85878E", "12px"),
            new TbTableTotal("#333333"));

    public static final TbTableConfig newInstance() {
        return new TbTableConfig(DEFAULT);
    }

    private TbTableItem item;
    private TbBorderColorSize border;
    private TbTableHeading heading;
    private TbTableTotal total;

    public TbTableConfig(TbTableConfig other) {
        this.item = new TbTableItem(other.item);
        this.border = new TbBorderColorSize(other.border);
        this.heading = new TbTableHeading(other.heading);
        this.total = new TbTableTotal(other.total);
    }

    @Data
    @AllArgsConstructor
    public static class TbTableItem {
        private String color;
        private String size;
        private String lineHeight;

        public TbTableItem(TbTableItem other) {
            this.color = other.color;
            this.size = other.size;
            this.lineHeight = other.lineHeight;
        }
    }

    @Data
    @AllArgsConstructor
    public static class TbTableHeading {
        private String color;
        private String size;

        public TbTableHeading(TbTableHeading other) {
            this.color = other.color;
            this.size = other.size;
        }
    }

    @Data
    @AllArgsConstructor
    public static class TbTableTotal {
        private String color;

        public TbTableTotal(TbTableTotal other) {
            this.color = other.color;
        }
    }
}

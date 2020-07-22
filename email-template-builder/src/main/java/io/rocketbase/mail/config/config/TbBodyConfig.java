package io.rocketbase.mail.config.config;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TbBodyConfig {

    static final TbBodyConfig DEFAULT = new TbBodyConfig("#F4F4F7",
            new TbBodyBorder("1px", "#EAEAEC"),
            new TbBodyDark("#333333", "#FFF"));

    public static final TbBodyConfig newInstance() {
        return new TbBodyConfig(DEFAULT);
    }

    private String background;
    private TbBodyBorder border;
    private TbBodyDark dark;

    public TbBodyConfig(TbBodyConfig other) {
        this.background = other.background;
        this.border = new TbBodyBorder(other.border);
        this.dark = new TbBodyDark(other.dark);
    }

    @Data
    @AllArgsConstructor
    public static class TbBodyBorder {
        private String size;
        private String color;

        public TbBodyBorder(TbBodyBorder other) {
            this.size = other.size;
            this.color = other.color;
        }
    }

    @Data
    @AllArgsConstructor
    public static class TbBodyDark {
        private String background;
        private String color;

        public TbBodyDark(TbBodyDark other) {
            this.background = other.background;
            this.color = other.color;
        }
    }
}

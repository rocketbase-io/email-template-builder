package io.rocketbase.mail.config.config;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TbBoxConfig {

    static final TbBoxConfig DEFAULT = new TbBoxConfig("24px",
            "#F4F4F7",
            "2px dashed #CBCCCF",
            new TbBoxDark("#222"));

    public static final TbBoxConfig newInstance() {
        return new TbBoxConfig(DEFAULT);
    }

    private String padding;
    private String background;
    private String border;
    private TbBoxDark dark;

    public TbBoxConfig(TbBoxConfig other) {
        this.padding = other.padding;
        this.background = other.background;
        this.border = other.border;
        this.dark = new TbBoxDark(other.dark);
    }

    @Data
    @AllArgsConstructor
    public static class TbBoxDark {
        private String background;

        public TbBoxDark(TbBoxDark other) {
            this.background = other.background;
        }
    }
}

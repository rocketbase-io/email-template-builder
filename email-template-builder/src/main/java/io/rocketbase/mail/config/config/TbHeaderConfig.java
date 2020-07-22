package io.rocketbase.mail.config.config;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TbHeaderConfig {

    static final TbHeaderConfig DEFAULT = new TbHeaderConfig("25px 0",
            "center",
            "16px",
            "bold",
            "#A8AAAF",
            "0 1px 0 white");

    public static final TbHeaderConfig newInstance() {
        return new TbHeaderConfig(DEFAULT);
    }

    private String padding;
    private String align;
    private String size;
    private String weight;
    private String color;
    private String textShadow;

    public TbHeaderConfig(TbHeaderConfig other) {
        this.padding = other.padding;
        this.align = other.align;
        this.size = other.size;
        this.weight = other.weight;
        this.color = other.color;
        this.textShadow = other.textShadow;
    }
}

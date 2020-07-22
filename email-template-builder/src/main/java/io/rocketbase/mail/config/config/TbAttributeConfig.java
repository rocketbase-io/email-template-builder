package io.rocketbase.mail.config.config;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TbAttributeConfig {

    static final TbAttributeConfig DEFAULT = new TbAttributeConfig("0 0 21px",
            "#F4F4F7",
            "16px");

    public static final TbAttributeConfig newInstance() {
        return new TbAttributeConfig(DEFAULT);
    }

    private String margin;
    private String background;
    private String padding;

    public TbAttributeConfig(TbAttributeConfig other) {
        this.margin = other.margin;
        this.background = other.background;
        this.padding = other.padding;
    }
}

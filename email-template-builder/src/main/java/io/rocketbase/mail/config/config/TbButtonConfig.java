package io.rocketbase.mail.config.config;

import io.rocketbase.mail.config.base.TbBackgroundColor;
import io.rocketbase.mail.config.base.TbBorderDetailed;
import io.rocketbase.mail.styling.ColorStyle;
import lombok.Getter;

@Getter
public class TbButtonConfig extends TbBackgroundColor {

    public static final TbButtonConfig DEFAULT = new TbButtonConfig(new TbBorderDetailed("10px", "18px", "10px", "18px"),
            "3px", "0 2px 3px rgba(0, 0, 0, 0.16)",
            new TbBackgroundColor(ColorStyle.BLUE, ColorStyle.WHITE));

    private final TbBorderDetailed border;
    private final String borderRadius;
    private final String boxShadow;

    public TbButtonConfig(TbButtonConfig other) {
        super(other);
        this.border = other.border;
        this.borderRadius = other.borderRadius;
        this.boxShadow = other.boxShadow;
    }

    public TbButtonConfig(TbBorderDetailed border, String borderRadius, String boxShadow, TbBackgroundColor defaultColor) {
        super(defaultColor);
        this.border = border;
        this.borderRadius = borderRadius;
        this.boxShadow = boxShadow;
    }
}

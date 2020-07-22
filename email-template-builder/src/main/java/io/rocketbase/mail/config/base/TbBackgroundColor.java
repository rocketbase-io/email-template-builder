package io.rocketbase.mail.config.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TbBackgroundColor {
    private String background;
    private String color;

    public TbBackgroundColor(TbBackgroundColor other) {
        this.background = other.background;
        this.color = other.color;
    }
}

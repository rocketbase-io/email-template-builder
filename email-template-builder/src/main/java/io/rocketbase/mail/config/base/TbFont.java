package io.rocketbase.mail.config.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TbFont {
    private String size;
    private String color;

    public TbFont(TbFont other) {
        this.size = other.size;
        this.color = other.color;
    }

}

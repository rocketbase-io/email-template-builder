package io.rocketbase.mail.config.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TbBorderColorSize {
    private String color;
    private String size;

    public TbBorderColorSize(TbBorderColorSize other) {
        this.color = other.color;
        this.size = other.size;
    }
}

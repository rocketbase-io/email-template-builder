package io.rocketbase.mail.config.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TbBorderDetailed {
    private String top;
    private String right;
    private String bottom;
    private String left;

    public TbBorderDetailed(TbBorderDetailed other) {
        this.top = other.top;
        this.right = other.right;
        this.bottom = other.bottom;
        this.left = other.left;
    }
}

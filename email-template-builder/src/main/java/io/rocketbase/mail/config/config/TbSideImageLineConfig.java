package io.rocketbase.mail.config.config;

import io.rocketbase.mail.styling.ObjectFit;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TbSideImageLineConfig {

    static final TbSideImageLineConfig DEFAULT = new TbSideImageLineConfig(ObjectFit.NONE);

    private ObjectFit objectFit;

    public TbSideImageLineConfig(TbSideImageLineConfig other) {
        this.objectFit = other.objectFit;
    }

    public static TbSideImageLineConfig newInstance() {
        return new TbSideImageLineConfig(DEFAULT);
    }
}

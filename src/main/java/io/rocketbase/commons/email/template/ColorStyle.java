package io.rocketbase.commons.email.template;

import io.rocketbase.commons.colors.ColorPalette;
import io.rocketbase.commons.colors.RgbColor;
import lombok.Getter;

@Getter
public class ColorStyle {

    private static final String WHITE = "ffffff";
    private static final String BLACK = "000000";

    public static ColorStyle BASE_STYLE = new ColorStyle(WHITE, "457B9D");
    public static ColorStyle WARNING_STYLE = new ColorStyle(WHITE, "ff9f00");

    private final String text;

    private final String bg;

    /**
     * removes automatically leading #
     */
    public ColorStyle(String text, String bg) {
        this.text = clearStartingHash(text);
        this.bg = clearStartingHash(bg);
    }

    public ColorStyle(ColorPalette colorPalette) {
        this.text = colorPalette.isBlackContrastingColor() ? BLACK : WHITE;
        this.bg = colorPalette.getHexCode();
    }

    /**
     * specify background color - code detect automatically the text-color (black/white)<br>
     * format could be #fff, ffffff, rgb(255,255,255)
     */
    public ColorStyle(String bg) {
        RgbColor color = RgbColor.hex2rgb(bg);
        if (color == null) {
            color = RgbColor.readRgb(bg);
        }
        this.text = color.isBlackContrastingColor() ? BLACK : WHITE;
        this.bg = color.getHexCode();
    }

    protected String clearStartingHash(String value) {
        if (value == null) {
            return null;
        }
        return value.startsWith("#") ? value.substring(1) : value;
    }
}

package io.rocketbase.mail.styling;

import io.rocketbase.commons.colors.ColorPalette;
import io.rocketbase.commons.colors.RgbColor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Collection;

@Getter
public class ColorStyleSimple implements ColorStyle {

    public static ColorStyleSimple BASE_STYLE = new ColorStyleSimple(WHITE, BLUE);
    public static ColorStyleSimple WARNING_STYLE = new ColorStyleSimple(WHITE, YELLOW);

    public static ColorStyleSimple BLUE_STYLE = new ColorStyleSimple(WHITE, BLUE);
    public static ColorStyleSimple GREEN_STYLE = new ColorStyleSimple(WHITE, GREEN);
    public static ColorStyleSimple RED_STYLE = new ColorStyleSimple(WHITE, RED);
    public static ColorStyleSimple YELLOW_STYLE = new ColorStyleSimple(BLACK, YELLOW);
    public static ColorStyleSimple BLACK_STYLE = new ColorStyleSimple(WHITE, DARK);
    public static ColorStyleSimple GRAY_STYLE = new ColorStyleSimple(BLACK, GRAY);

    private final String text;
    private final String bg;

    /**
     * removes automatically leading #
     */
    public ColorStyleSimple(String text, String bg) {
        this.text = text;
        this.bg = bg;
    }

    public ColorStyleSimple(ColorPalette colorPalette) {
        this.text = colorPalette.isBlackContrastingColor() ? BLACK : WHITE;
        this.bg = "#" + colorPalette.getHexCode();
    }

    /**
     * specify background color - code detect automatically the text-color (black/white)<br>
     * format could be #fff, ffffff, rgb(255,255,255)
     */
    public ColorStyleSimple(String bg) {
        RgbColor color = RgbColor.readRgbOrHex(bg);
        if (color == null) {
            throw new RuntimeException("invalid bg value!");
        }
        this.text = color.isBlackContrastingColor() ? BLACK : WHITE;
        this.bg = "#" + color.getHexCode();
    }

    public static Collection<ColorStyle> getAllPresets( ) {
        return Arrays.asList(BASE_STYLE, WARNING_STYLE, BLACK_STYLE, GREEN_STYLE, RED_STYLE, YELLOW_STYLE, BLACK_STYLE, GRAY_STYLE);
    }
}
